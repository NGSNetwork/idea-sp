package net.neogenesisnetwork.sp.lang

import com.intellij.openapi.fileTypes.LanguageFileType

object SourcePawnFileType : LanguageFileType(SourcePawnLanguage.INSTANCE) {
    override fun getName() = "SourcePawn file"
    override fun getDescription() = "SourcePawn language file"
    override fun getDefaultExtension() = "sp"
    override fun getIcon() = SourcePawnIcons.FILE
}
