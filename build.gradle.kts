import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.*
import java.nio.file.Paths

val isCI = !System.getenv("CI").isNullOrBlank()
val commitHash = kotlin.run {
    val process: Process = Runtime.getRuntime().exec("git rev-parse --short HEAD")
    process.waitFor()
    val output = process.inputStream.use {
        process.inputStream.use { it.readBytes().let(::String) }
    }
    process.destroy()
    output.trim()
}

val pluginComingVersion = "0.0.1"
val pluginVersion = if (isCI) "$pluginComingVersion-$commitHash" else pluginComingVersion
val packageName = "tf.ngs.sp"

group = packageName
version = pluginVersion

plugins {
    java
    id("org.jetbrains.intellij") version "0.4.14"
    id("org.jetbrains.grammarkit") version "2019.3"
    kotlin("jvm") version "1.3.60"
}

fun fromToolbox(root: String, ide: String) = file(root)
        .resolve(ide)
        .takeIf { it.exists() }
        ?.resolve("ch-0")
        ?.listFiles()
        .orEmpty()
        .filterNotNull()
        .filter { it.isDirectory }
        .filterNot { it.name.endsWith(".plugins") }
        .maxBy {
            val (major, minor, patch) = it.name.split('.')
            String.format("%5s%5s%5s", major, minor, patch)
        }
        ?.also { println("Picked: $it") }

allprojects {
    apply { plugin("org.jetbrains.grammarkit") }
}

grammarKit {
    grammarKitRelease = "7aecfcd72619e9c241866578e8312f339b4ddbd8"
}

intellij {
    updateSinceUntilBuild = false
    instrumentCode = true
    if (!isCI) {
        setPlugins("PsiViewer:193-SNAPSHOT", "java")
        downloadSources = true
    } else setPlugins("java")
    val user = System.getProperty("user.name")
    val os = System.getProperty("os.name")
    val root = when {
        os.startsWith("Windows") -> "C:\\Users\\$user\\AppData\\Local\\JetBrains\\Toolbox\\apps"
        os == "Linux" -> "/home/$user/.local/share/JetBrains/Toolbox/apps"
        else -> return@intellij
    }
    val intellijPath = sequenceOf("IDEA-C", "IDEA-U")
            .mapNotNull { fromToolbox(root, it) }.firstOrNull()
    intellijPath?.absolutePath?.let { localPath = it }
    val pycharmPath = sequenceOf("PyCharm-C", "IDEA-C", "IDEA-U")
            .mapNotNull { fromToolbox(root, it) }.firstOrNull()
    pycharmPath?.absolutePath?.let { alternativeIdePath = it }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<PatchPluginXmlTask> {
    changeNotes(file("docs/change-notes.html").readText())
    version(pluginVersion)
    pluginId(packageName)
}

sourceSets {
    main {
        withConvention(KotlinSourceSet::class) {
            listOf(java, kotlin).forEach { it.srcDirs("src", "gen") }
        }
        resources.srcDir("res")
    }

    test {
        withConvention(KotlinSourceSet::class) {
            listOf(java, kotlin).forEach { it.srcDirs("test") }
        }
        resources.srcDir("testData")
    }
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/jetbrains/markdown/")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(group = "org.eclipse.mylyn.github", name = "org.eclipse.egit.github.core", version = "2.1.5") {
        exclude(module = "gson")
    }
    compile("org.jetbrains", "markdown", "0.1.31")
    testCompile(kotlin(module = "test-junit"))
    testCompile(group = "junit", name = "junit", version = "4.12")
}

task("displayCommitHash") {
    group = "help"
    description = "Display the newest commit hash"
    doFirst { println("Commit hash: $commitHash") }
}

task("isCI") {
    group = "help"
    description = "Check if it's running in a continuous-integration"
    doFirst { println(if (isCI) "Yes, I'm on a CI." else "No, I'm not on CI.") }
}

// Don't specify type explicitly. Will be incorrectly recognized
val parserRoot = Paths.get("tf", "ngs", "sp", "lang")
val lexerRoot = Paths.get("gen", "tf", "ngs", "sp", "lang")
fun path(more: Iterable<*>) = more.joinToString(File.separator)
fun bnf(name: String) = Paths.get("grammar", "$name-grammar.bnf").toString()
fun flex(name: String) = Paths.get("grammar", "$name-lexer.flex").toString()

val genParser = task<GenerateParser>("genParser") {
    group = tasks["init"].group!!
    description = "Generate the Parser and PsiElement classes"
    source = bnf("sourcepawn")
    targetRoot = "gen/"
    pathToParser = path(parserRoot + "SourcePawnParser.java")
    pathToPsiRoot = path(parserRoot + "psi")
    purgeOldFiles = true
}

val genLexer = task<GenerateLexer>("genLexer") {
    group = genParser.group
    description = "Generate the Lexer"
    source = flex("sourcepawn")
    targetDir = path(lexerRoot)
    targetClass = "SourcePawnLexer"
    purgeOldFiles = true
    dependsOn(genParser)
}

val cleanGenerated = task("cleanGenerated") {
    group = tasks["clean"].group
    description = "Remove all generated codes"
    doFirst { delete("gen") }
}

tasks.withType<KotlinCompile> {
    dependsOn(
            genParser,
            genLexer
    )
    kotlinOptions {
        jvmTarget = "1.8"
        languageVersion = "1.3"
        apiVersion = "1.3"
        freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
}

tasks.withType<Delete> { dependsOn(cleanGenerated) }
