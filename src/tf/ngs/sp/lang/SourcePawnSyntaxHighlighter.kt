package tf.ngs.sp.lang

import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

import java.util.HashMap

import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import tf.ngs.sp.lang.SourcePawnTokenTypeSets.Companion.COMMENTS
import tf.ngs.sp.lang.SourcePawnTokenTypeSets.Companion.IDENTIFIERS
import tf.ngs.sp.lang.SourcePawnTokenTypeSets.Companion.KEYWORDS
import tf.ngs.sp.lang.SourcePawnTokenTypeSets.Companion.NUMBERS
import tf.ngs.sp.lang.SourcePawnTokenTypeSets.Companion.OPERATORS
import tf.ngs.sp.lang.SourcePawnTokenTypeSets.Companion.PARENTHESES
import tf.ngs.sp.lang.psi.SourcePawnTypes

class SourcePawnSyntaxHighlighter : SyntaxHighlighterBase() {


    override fun getHighlightingLexer(): Lexer {
        return FlexAdapter(SourcePawnLexer(null))
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        // I'm not sure what tokenType is supposed to be, because it doesn't
        // seem to match STORAGE_CLASS or LITERAL

        return pack(ATTRIBUTES[tokenType])
    }

    companion object {
        internal val OPERATOR = createTextAttributesKey("SOURCE_PAWN_ASSIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        internal val LITERAL = createTextAttributesKey("SOURCE_PAWN_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT)
        internal val RESERVED = createTextAttributesKey("SOURCE_PAWN_RESERVED", DefaultLanguageHighlighterColors.KEYWORD)
        internal val COMMENT = createTextAttributesKey("SOURCE_PAWN_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        internal val IDENTIFIER = createTextAttributesKey("SOURCE_PAWN_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)

        private val ATTRIBUTES = HashMap<IElementType, TextAttributesKey>()

        init {
            fillMap(ATTRIBUTES, NUMBERS, DefaultLanguageHighlighterColors.NUMBER)
            fillMap(ATTRIBUTES, KEYWORDS, RESERVED)
            fillMap(ATTRIBUTES, COMMENTS, COMMENT)
            fillMap(ATTRIBUTES, OPERATORS, OPERATOR)
            fillMap(ATTRIBUTES, IDENTIFIERS, IDENTIFIER)
            fillMap(ATTRIBUTES, PARENTHESES, DefaultLanguageHighlighterColors.PARENTHESES)

            ATTRIBUTES[SourcePawnTypes.STRING_LITERAL] = DefaultLanguageHighlighterColors.STRING
            ATTRIBUTES[SourcePawnTypes.SEMICOLON] = DefaultLanguageHighlighterColors.SEMICOLON
            ATTRIBUTES[SourcePawnTypes.BLOCK_COMMENT] = DefaultLanguageHighlighterColors.BLOCK_COMMENT
            ATTRIBUTES[SourcePawnTypes.LINE_COMMENT] = DefaultLanguageHighlighterColors.LINE_COMMENT
            ATTRIBUTES[SourcePawnTypes.COMMA] = DefaultLanguageHighlighterColors.COMMA;
            ATTRIBUTES[SourcePawnTypes.DOT] = DefaultLanguageHighlighterColors.DOT;
            ATTRIBUTES[SourcePawnTypes.GLOBAL_VAR_NAME] = DefaultLanguageHighlighterColors.GLOBAL_VARIABLE
            ATTRIBUTES[SourcePawnTypes.FUNCTION_NAME] = DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
            ATTRIBUTES[SourcePawnTypes.CALL_EXPR_R] = DefaultLanguageHighlighterColors.FUNCTION_CALL
            ATTRIBUTES[SourcePawnTypes.FUNCTION_PARAMETER] = DefaultLanguageHighlighterColors.PARAMETER
            ATTRIBUTES[SourcePawnTypes.METHODMAP_NAME] = DefaultLanguageHighlighterColors.CLASS_NAME
        }
    }
}
