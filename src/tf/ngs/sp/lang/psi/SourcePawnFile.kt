package tf.ngs.sp.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import tf.ngs.sp.lang.SourcePawnFileType
import tf.ngs.sp.lang.SourcePawnLanguage

class SourcePawnFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SourcePawnLanguage.INSTANCE) {
    override fun getFileType() = SourcePawnFileType
    override fun toString() = "SourcePawn File"
}
