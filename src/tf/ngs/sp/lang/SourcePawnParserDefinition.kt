package tf.ngs.sp.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.FlexAdapter
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import tf.ngs.sp.lang.psi.SourcePawnFile
import tf.ngs.sp.lang.psi.SourcePawnTypes

class SourcePawnParserDefinition : ParserDefinition {

    override fun createLexer(project: Project) = FlexAdapter(SourcePawnLexer(null))

    override fun getWhitespaceTokens() = WHITE_SPACES
    override fun getCommentTokens() = COMMENTS
    override fun getStringLiteralElements() = STRING_LITERALS

    override fun createParser(project: Project): PsiParser = SourcePawnParser()

    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider) = SourcePawnFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode) = ParserDefinition.SpaceRequirements.MAY

    override fun createElement(node: ASTNode): PsiElement = SourcePawnTypes.Factory.createElement(node)

    companion object {
        private val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        private val COMMENTS = TokenSet.create(SourcePawnTypes.LINE_COMMENT, SourcePawnTypes.BLOCK_COMMENT, SourcePawnTypes.PREPROCESSOR_COMMENT)
        private val STRING_LITERALS = TokenSet.create(SourcePawnTypes.STRING_LITERAL, SourcePawnTypes.CHARACTER_LITERAL)

        private val FILE = IFileElementType(SourcePawnLanguage.INSTANCE)
    }
}
