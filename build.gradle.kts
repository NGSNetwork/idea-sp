import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.file.Paths

val commitHash by lazy {
    val output: String
    val process: Process = Runtime.getRuntime().exec("git rev-parse --short HEAD")
    process.waitFor()
    output = process.inputStream.use {
        it.bufferedReader().use {
            it.readText()
        }
    }
    process.destroy()
    output.trim()
}

val isCI = !System.getenv("CI").isNullOrBlank()

val pluginComingVersion = "0.0.1"
val packageName = "tf.ngs.sp"
val ideaVersion = "2019.3"
val customSinceBuild = "193.4386"
val customUntilBuild = "201.*"
val buildNumber = "SNAPSHOT"
val pluginVersion = pluginComingVersion

group = packageName
version = pluginVersion
plugins {
    java
    id("org.jetbrains.intellij") version "0.4.15"
    id("org.jetbrains.grammarkit") version "2019.3"
    kotlin("jvm") version "1.3.61"
}

fun fromToolbox(root: String, ide: String) = file(root)
        .resolve(ide)
        .takeIf { it.exists() }
        ?.resolve("ch-0")
        ?.listFiles()
        .orEmpty()
        .filterNotNull()
        .filter { it.isDirectory }
        .maxBy {
            val (major, minor, patch) = it.name.split('.')
            String.format("%5s%5s%5s", major, minor, patch)
        }
        ?.also { println("Picked: $it") }

allprojects {
    apply { plugin("org.jetbrains.grammarkit") }

    intellij {
        updateSinceUntilBuild = false
        instrumentCode = true
        version = "2019.1"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<PatchPluginXmlTask> {
    version(pluginVersion)
    pluginId(packageName)
    println(pluginId)
}

sourceSets {
    main {
        withConvention(KotlinSourceSet::class) {
            listOf(java, kotlin).forEach { it.srcDirs("src", "gen") }
        }
        resources.srcDir("resources")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}

task("displayCommitHash") {
    group = "help"
    description = "Display the newest commit hash"
    doFirst {
        println("Commit hash: $commitHash")
    }
}

task("isCI") {
    group = "help"
    description = "Check if it's running in a continuous-integration"
    doFirst {
        println(if (isCI) "Yes, I'm on a CI." else "No, I'm not on CI.")
    }
}

// Don't specify type explicitly. Will be incorrectly recognized
val parserRoot = Paths.get("gen", "tf", "ngs", "sp", "lang")
val lexerRoot = Paths.get("gen", "tf", "ngs", "sp", "lang")
fun path(more: Iterable<*>) = more.joinToString(File.separator)
fun bnf(name: String) = Paths.get("grammar", "$name-grammar.bnf").toString()
fun flex(name: String) = Paths.get("grammar", "$name-lexer.flex").toString()

val genParser = task<GenerateParser>("genParser") {
    group = tasks["init"].group!!
    description = "Generate the Parser and PsiElement classes"
    source = bnf("sourcepawn")
    targetRoot = "gen"
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
}

val cleanGenerated = task("cleanGenerated") {
    group = tasks["clean"].group
    description = "Remove all generated codes"
    doFirst {
        delete("gen")
    }
}

tasks.withType<KotlinCompile> {
    dependsOn(genLexer)
    dependsOn(genParser)
    kotlinOptions {
        jvmTarget = "1.8"
        languageVersion = "1.3"
        apiVersion = "1.3"
    }
}

tasks.withType<Delete> {
    dependsOn(cleanGenerated)
}
