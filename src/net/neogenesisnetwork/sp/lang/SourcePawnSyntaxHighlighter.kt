package net.neogenesisnetwork.sp.lang

import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import net.neogenesisnetwork.sp.lang.psi.SourcePawnTypes

import java.util.HashMap

import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import net.neogenesisnetwork.sp.lang.SourcePawnTokenTypeSets.Companion.COMMENTS
import net.neogenesisnetwork.sp.lang.SourcePawnTokenTypeSets.Companion.KEYWORDS

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

        private val ATTRIBUTES = HashMap<IElementType, TextAttributesKey>()

        init {
            ATTRIBUTES[SourcePawnTypes.EQ] = OPERATOR
            ATTRIBUTES[SourcePawnTypes.LITERAL] = LITERAL

            fillMap(ATTRIBUTES, KEYWORDS, RESERVED)
            fillMap(ATTRIBUTES, COMMENTS, COMMENT)
        }
    }
}
