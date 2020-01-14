package tf.ngs.sp.lang

import com.intellij.psi.tree.TokenSet
import tf.ngs.sp.lang.psi.SourcePawnTypes.*

interface SourcePawnTokenTypeSets {
    companion object {
        // Literal types without bool keywords
        val NUMBERS = TokenSet.create(
                INTEGER_LITERAL,
                HEX_LITERAL,
                FLOAT_LITERAL
        )

        // Operator types
        val OPERATORS = TokenSet.create(
                EQ,
                EQEQ,
                NE,
                EXCL,
                PLUS,
                MINUS,
                PLUSEQ,
                MINUSEQ,
                DIV,
                DIVEQ,
                LT,
                LE,
                LTLT,
                LTLTEQ,
                GT,
                GE,
                GTGT,
                GTGTEQ
        )

        // comment types
        val COMMENTS = TokenSet.create(
                LINE_COMMENT,
                BLOCK_COMMENT,
                PREPROCESSOR_COMMENT
        )

        // keywords
        val KEYWORDS = TokenSet.create(
                ASSERT_KEYWORD,
                BREAK_KEYWORD,
                CASE_KEYWORD,
                CONTINUE_KEYWORD,
                DEFAULT_KEYWORD,
                DO_KEYWORD,
                ELSE_KEYWORD,
                EXIT_KEYWORD,
                FOR_KEYWORD,
                GOTO_KEYWORD,
                IF_KEYWORD,
                RETURN_KEYWORD,
                SLEEP_KEYWORD,
                STATE_KEYWORD,
                SWITCH_KEYWORD,
                WHILE_KEYWORD,
                DEFINED_KEYWORD,
                STATE_KEYWORD,
                TAGOF_KEYWORD,
                CONST_KEYWORD,
                FORWARD_KEYWORD,
                NATIVE_KEYWORD,
                NEW_KEYWORD,
                OPERATOR_KEYWORD,
                PUBLIC_KEYWORD,
                STATIC_KEYWORD,
                STOCK_KEYWORD,
                DECL_KEYWORD,
                NEW_KEYWORD,
                PUBLIC_KEYWORD,
                BOOL_KEYWORD,
                INT_KEYWORD,
                CHAR_KEYWORD,
                VOID_KEYWORD,
                DELETE_KEYWORD,
                FUNCENUM_KEYWORD,
                FUNCTAG_KEYWORD,
                FLOAT_KEYWORD,
                BOOL_LITERAL
        )

        val PARENTHESES = TokenSet.create(
                LPAREN,
                RPAREN
        )

        val IDENTIFIERS = TokenSet.create(SYMBOL)
    }
}
