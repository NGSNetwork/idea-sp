package org.idea_sp;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.idea_sp.psi.SourcePawnTypes;
import com.intellij.psi.TokenType;

%%

%{
  public SourcePawnLexer() {
    this(null);
  }
%}

%public
%class SourcePawnLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%eof{  return;
%eof}

EOL="\n"|"\r\n"
LINE_WS=[ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+

SPACE=[ \n\r\t\f]
LINE_COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/\*~(\*/)"
PREPROCESSOR_COMMENT=#(assert|define|else|elseif|endif|endinput|error|file|if|include|line|pragma|section|tryinclude|undef)[^\r\n]*
INTEGER_LITERAL=[-+]?[0-9][_\d]*
FLOAT_LITERAL=[-+]?[0-9][_\d]*\.[0-9][_\d]*(e[-+]?[0-9]+)?
HEX_LITERAL=[-+]?0x[a-fA-F0-9_]+
BINARY_LITERAL=[-+]?0b[01_]+
STRING_LITERAL=\"(\\.|[^\"])*\"
CHARACTER_LITERAL='(\\.|[^\"])'
SYMBOL=([@_a-zA-Z][@_a-zA-Z0-9]+|[a-zA-Z][@_a-zA-Z0-9]*)

EQ="="
EXCL="!"
TILDE="~"
QUEST="?"
COLON=":"
PLUS="+"
MINUS="-"
ASTERISK="*"
DIV="/"
OR="|"
XOR="^"
PERC="%"
LPAREN="("
RPAREN=")"
LBRACE="{"
RBRACE="}"
LBRACKET="["
RBRACKET="]"
SEMICOLON=";"
COMMA=","
ELLIPSIS="..."
DOT="."
EQEQ="=="
NE="!="
OROR="||"
PLUSPLUS="++"
MINUSMINUS="--"
LT="<"
LE="<="
LTLT="<<"
GT=">"
AND="&"
ANDAND="&&"
PLUSEQ="+="
MINUSEQ="-="
ASTERISKEQ="*="
DIVEQ="/="
ANDEQ="&="
OREQ="|="
XOREQ="^="
PERCEQ="%="
LTLTEQ="<<="
GTGTEQ=">>="

ASSERT_KEYWORD="assert"
BREAK_KEYWORD="break"
CASE_KEYWORD="case"
CONTINUE_KEYWORD="continue"
DEFAULT_KEYWORD="default"
DO_KEYWORD="do"
ELSE_KEYWORD="else"
EXIT_KEYWORD="exit"
FOR_KEYWORD="for"
GOTO_KEYWORD="goto"
IF_KEYWORD="if"
RETURN_KEYWORD="return"
SLEEP_KEYWORD="sleep"
STATE_KEYWORD="state"
SWITCH_KEYWORD="switch"
WHILE_KEYWORD="while"

DEFINED_KEYWORD="defined"
SIZEOF_KEYWORD="sizeof"
STATE_KEYWORD="state"
TAGOF_KEYWORD="tagof"

CONST_KEYWORD="const"
FORWARD_KEYWORD="forward"
NATIVE_KEYWORD="native"
NEW_KEYWORD="new"
OPERATOR_KEYWORD="operator"
PUBLIC_KEYWORD="public"
STATIC_KEYWORD="static"
STOCK_KEYWORD="stock"

CHAR_KEYWORD="char"
INT_KEYWORD="int"
FLOAT_KEYWORD="float"
BOOL_KEYWORD="bool"
VOID_KEYWORD="void"
ANY_KEYWORD="any"

DECL_KEYWORD="decl"
NEW_KEYWORD="new"
PUBLIC_KEYWORD="public"
FUNCTAG_KEYWORD="functag"

// SourcePawn-specific keywords
ENUM_KEYWORD="enum"
STRUCT_KEYWORD="struct"

SPACE="regexp:[ \n\r\t\f]"

line_comment="regexp://[^\r\n]*"
block_comment="regexp:/\*~(\*/)"
preprocessor_comment="regexp:#(assert|define|else|elseif|endif|endinput|error|file|if|include|line|pragma|section|tryinclude|undef)[^\r\n]*"

integer_literal="regexp:[-+]?\d[_\d]*"
float_literal="regexp:[-+]?\d[_\d]*\.\d[_\d]*(e[-+]?\d+)?"
hex_literal="regexp:[-+]?0x[a-fA-F0-9_]+"
binary_literal="regexp:[-+]?0b[01_]+"

string_literal="regexp:\"(\\.|[^\"])*\""
character_literal="regexp:'(\\.|[^\"])'"

// @ and _ are not themselves valid identifiers.
identifier="regexp:([@_a-zA-Z][@_a-zA-Z0-9]+|[a-zA-Z][@_a-zA-Z0-9]*)"


// Below is from an attempt to use dvander's grammar as a base
// See https://wiki.alliedmods.net/SourcePawn_Transitional_Syntax#Grammar

// This is dvander's symbol. It doesn't handle all the edge cases Pawn allows (the one below it tries to)
//    symbol="regexp:[A-Za-z_]([A-Za-z0-9_]*)"

symbol="regexp:([@_a-zA-Z][@_a-zA-Z0-9]+|[a-zA-Z][@_a-zA-Z0-9]*)"

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return TokenType.WHITE_SPACE; }

  "="                         { return SourcePawnTypes.EQ; }
  "!"                         { return SourcePawnTypes.EXCL; }
  "~"                         { return SourcePawnTypes.TILDE; }
  "?"                         { return SourcePawnTypes.QUEST; }
  ":"                         { return SourcePawnTypes.COLON; }
  "+"                         { return SourcePawnTypes.PLUS; }
  "-"                         { return SourcePawnTypes.MINUS; }
  "*"                         { return SourcePawnTypes.ASTERISK; }
  "/"                         { return SourcePawnTypes.DIV; }
  "|"                         { return SourcePawnTypes.OR; }
  "^"                         { return SourcePawnTypes.XOR; }
  "%"                         { return SourcePawnTypes.PERC; }
  "("                         { return SourcePawnTypes.LPAREN; }
  ")"                         { return SourcePawnTypes.RPAREN; }
  "{"                         { return SourcePawnTypes.LBRACE; }
  "}"                         { return SourcePawnTypes.RBRACE; }
  "["                         { return SourcePawnTypes.LBRACKET; }
  "]"                         { return SourcePawnTypes.RBRACKET; }
  ";"                         { return SourcePawnTypes.SEMICOLON; }
  ","                         { return SourcePawnTypes.COMMA; }
  "..."                       { return SourcePawnTypes.ELLIPSIS; }
  "."                         { return SourcePawnTypes.DOT; }
  "=="                        { return SourcePawnTypes.EQEQ; }
  "!="                        { return SourcePawnTypes.NE; }
  "||"                        { return SourcePawnTypes.OROR; }
  "++"                        { return SourcePawnTypes.PLUSPLUS; }
  "--"                        { return SourcePawnTypes.MINUSMINUS; }
  "<"                         { return SourcePawnTypes.LT; }
  "<="                        { return SourcePawnTypes.LE; }
  "<<"                        { return SourcePawnTypes.LTLT; }
  ">"                         { return SourcePawnTypes.GT; }
  "&"                         { return SourcePawnTypes.AND; }
  "&&"                        { return SourcePawnTypes.ANDAND; }
  "+="                        { return SourcePawnTypes.PLUSEQ; }
  "-="                        { return SourcePawnTypes.MINUSEQ; }
  "*="                        { return SourcePawnTypes.ASTERISKEQ; }
  "/="                        { return SourcePawnTypes.DIVEQ; }
  "&="                        { return SourcePawnTypes.ANDEQ; }
  "|="                        { return SourcePawnTypes.OREQ; }
  "^="                        { return SourcePawnTypes.XOREQ; }
  "%="                        { return SourcePawnTypes.PERCEQ; }
  "<<="                       { return SourcePawnTypes.LTLTEQ; }
  ">>="                       { return SourcePawnTypes.GTGTEQ; }
  "assert"                    { return SourcePawnTypes.ASSERT_KEYWORD; }
  "break"                     { return SourcePawnTypes.BREAK_KEYWORD; }
  "case"                      { return SourcePawnTypes.CASE_KEYWORD; }
  "continue"                  { return SourcePawnTypes.CONTINUE_KEYWORD; }
  "default"                   { return SourcePawnTypes.DEFAULT_KEYWORD; }
  "do"                        { return SourcePawnTypes.DO_KEYWORD; }
  "else"                      { return SourcePawnTypes.ELSE_KEYWORD; }
  "exit"                      { return SourcePawnTypes.EXIT_KEYWORD; }
  "for"                       { return SourcePawnTypes.FOR_KEYWORD; }
  "goto"                      { return SourcePawnTypes.GOTO_KEYWORD; }
  "if"                        { return SourcePawnTypes.IF_KEYWORD; }
  "return"                    { return SourcePawnTypes.RETURN_KEYWORD; }
  "sleep"                     { return SourcePawnTypes.SLEEP_KEYWORD; }
  "state"                     { return SourcePawnTypes.STATE_KEYWORD; }
  "switch"                    { return SourcePawnTypes.SWITCH_KEYWORD; }
  "while"                     { return SourcePawnTypes.WHILE_KEYWORD; }
  "defined"                   { return SourcePawnTypes.DEFINED_KEYWORD; }
  "sizeof"                    { return SourcePawnTypes.SIZEOF_KEYWORD; }
  "tagof"                     { return SourcePawnTypes.TAGOF_KEYWORD; }
  "const"                     { return SourcePawnTypes.CONST_KEYWORD; }
  "forward"                   { return SourcePawnTypes.FORWARD_KEYWORD; }
  "native"                    { return SourcePawnTypes.NATIVE_KEYWORD; }
  "new"                       { return SourcePawnTypes.NEW_KEYWORD; }
  "operator"                  { return SourcePawnTypes.OPERATOR_KEYWORD; }
  "public"                    { return SourcePawnTypes.PUBLIC_KEYWORD; }
  "static"                    { return SourcePawnTypes.STATIC_KEYWORD; }
  "stock"                     { return SourcePawnTypes.STOCK_KEYWORD; }
  "decl"                      { return SourcePawnTypes.DECL_KEYWORD; }
  "enum"                      { return SourcePawnTypes.ENUM_KEYWORD; }
  "struct"                    { return SourcePawnTypes.STRUCT_KEYWORD; }
  "expr"                      { return SourcePawnTypes.EXPR; }

  {SPACE}                     { return SourcePawnTypes.SPACE; }
  {LINE_COMMENT}              { return SourcePawnTypes.LINE_COMMENT; }
  {BLOCK_COMMENT}             { return SourcePawnTypes.BLOCK_COMMENT; }
  {PREPROCESSOR_COMMENT}      { return SourcePawnTypes.PREPROCESSOR_COMMENT; }
  {INTEGER_LITERAL}           { return SourcePawnTypes.INTEGER_LITERAL; }
  {FLOAT_LITERAL}             { return SourcePawnTypes.FLOAT_LITERAL; }
  {HEX_LITERAL}               { return SourcePawnTypes.HEX_LITERAL; }
  {BINARY_LITERAL}            { return SourcePawnTypes.BINARY_LITERAL; }
  {STRING_LITERAL}            { return SourcePawnTypes.STRING_LITERAL; }
  {CHARACTER_LITERAL}         { return SourcePawnTypes.CHARACTER_LITERAL; }
  {SYMBOL}                    { return SourcePawnTypes.SYMBOL; }

  [^] { return TokenType.BAD_CHARACTER; }
}
