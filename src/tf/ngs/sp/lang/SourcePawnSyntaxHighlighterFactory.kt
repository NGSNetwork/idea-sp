package tf.ngs.sp.lang

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import tf.ngs.sp.lang.SourcePawnSyntaxHighlighter


class SourcePawnSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = SourcePawnSyntaxHighlighter()
}
