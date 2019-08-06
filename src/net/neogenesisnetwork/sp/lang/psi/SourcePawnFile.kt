package net.neogenesisnetwork.sp.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import net.neogenesisnetwork.sp.lang.SourcePawnFileType
import net.neogenesisnetwork.sp.lang.SourcePawnLanguage

class SourcePawnFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SourcePawnLanguage.INSTANCE) {
    override fun getFileType() = SourcePawnFileType
    override fun toString() = "SourcePawn File"
}
