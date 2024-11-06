/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import Painter.WordPainter;

@SuppressWarnings("unused")

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    WordPainter painter;
    public ScannerF(String input, WordPainter painter) {
        yychar = 0;
        this.zzReader = new java.io.BufferedReader(
            new java.io.StringReader(input)
        );
        this.painter = painter;
    }
%}

//Directivas
%class ScannerF
%public
%cupsym TOK
%cup
%char
%full
%unicode

//Constructor
%init{
    yychar = 0;
%init}

//Expresiones regulares
UNUSED   = [ \r\t]+
ID       = (\_)*[a-zA-Z][a-zA-Z0-9\_]*
CHAR     = \'([^\n\'\\]|\\.)\'
STRING   = \"(([^\n\"\\]|\\.)*)\"
INTEGER  = [0-9]+
DOUBLE   = [0-9]+\.[0-9]+
COMMENTS = "//"([^\r\n]*)
COMMENTM = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%

/* 3. Reglas Semanticas */
// Reservadas
"main"              {return new Symbol(TOK.RW_main,      yychar, yylength(), yytext());}
"void"              {return new Symbol(TOK.RW_void,      yychar, yylength(), yytext());}
"String"            {return new Symbol(TOK.RW_String,    yychar, yylength(), yytext());}
"boolean"           {return new Symbol(TOK.RW_boolean,   yychar, yylength(), yytext());}
"char"              {return new Symbol(TOK.RW_char,      yychar, yylength(), yytext());}
"int"               {return new Symbol(TOK.RW_int,       yychar, yylength(), yytext());}
"double"            {return new Symbol(TOK.RW_double,    yychar, yylength(), yytext());}
"if"                {return new Symbol(TOK.RW_if,        yychar, yylength(), yytext());}
"else"              {return new Symbol(TOK.RW_else,      yychar, yylength(), yytext());}
"for"               {return new Symbol(TOK.RW_for,       yychar, yylength(), yytext());}
"while"             {return new Symbol(TOK.RW_while,     yychar, yylength(), yytext());}
"do"                {return new Symbol(TOK.RW_do,        yychar, yylength(), yytext());}
"switch"            {return new Symbol(TOK.RW_switch,    yychar, yylength(), yytext());}
"case"              {return new Symbol(TOK.RW_case,      yychar, yylength(), yytext());}
"default"           {return new Symbol(TOK.RW_default,   yychar, yylength(), yytext());}
"break"             {return new Symbol(TOK.RW_break,     yychar, yylength(), yytext());}
"continue"          {return new Symbol(TOK.RW_continue,  yychar, yylength(), yytext());}
"return"            {return new Symbol(TOK.RW_return,    yychar, yylength(), yytext());}
"true"              {return new Symbol(TOK.RW_true,      yychar, yylength(), yytext());}
"false"             {return new Symbol(TOK.RW_false,     yychar, yylength(), yytext());}
"print"             {return new Symbol(TOK.RW_print,     yychar, yylength(), yytext());}
// Valores
{CHAR}              {return new Symbol(TOK.TK_char,      yychar, yylength(), yytext());}
{STRING}            {return new Symbol(TOK.TK_string,    yychar, yylength(), yytext());}
{INTEGER}           {return new Symbol(TOK.TK_int,       yychar, yylength(), yytext());}
{DOUBLE}            {return new Symbol(TOK.TK_double,    yychar, yylength(), yytext());}
// Variables
{ID}                {return new Symbol(TOK.TK_id,        yychar, yylength(), yytext());}
// Incremento / Decremento
"++"                {return new Symbol(TOK.TK_inc,       yychar, yylength(), yytext());}
"--"                {return new Symbol(TOK.TK_dec,       yychar, yylength(), yytext());}
// Concatenacion
"+="                {return new Symbol(TOK.TK_add,       yychar, yylength(), yytext());}
"-="                {return new Symbol(TOK.TK_sub,       yychar, yylength(), yytext());}
// Operadores Aritméticos
"+"                 {return new Symbol(TOK.TK_plus,      yychar, yylength(), yytext());}
"-"                 {return new Symbol(TOK.TK_minus,     yychar, yylength(), yytext());}
"*"                 {return new Symbol(TOK.TK_mult,      yychar, yylength(), yytext());}
"/"                 {return new Symbol(TOK.TK_div,       yychar, yylength(), yytext());}
"^"                 {return new Symbol(TOK.TK_pow,       yychar, yylength(), yytext());}
"%"                 {return new Symbol(TOK.TK_mod,       yychar, yylength(), yytext());}
// Operadores Relacionales
"=="                {return new Symbol(TOK.TK_equequ,    yychar, yylength(), yytext());}
"!="                {return new Symbol(TOK.TK_notequ,    yychar, yylength(), yytext());}
"<="                {return new Symbol(TOK.TK_lessequ,   yychar, yylength(), yytext());}
">="                {return new Symbol(TOK.TK_moreequ,   yychar, yylength(), yytext());}
"="                 {return new Symbol(TOK.TK_equ,       yychar, yylength(), yytext());}
"<"                 {return new Symbol(TOK.TK_less,      yychar, yylength(), yytext());}
">"                 {return new Symbol(TOK.TK_more,      yychar, yylength(), yytext());}
// Operadores Lógicos
"&&"                {return new Symbol(TOK.TK_and,       yychar, yylength(), yytext());}
"||"                {return new Symbol(TOK.TK_or,        yychar, yylength(), yytext());}
"!"                 {return new Symbol(TOK.TK_not,       yychar, yylength(), yytext());}
// Símbolos de Agrupación
"("                 {return new Symbol(TOK.TK_lpar,      yychar, yylength(), yytext());}
")"                 {return new Symbol(TOK.TK_rpar,      yychar, yylength(), yytext());}
"{"                 {return new Symbol(TOK.TK_lbrc,      yychar, yylength(), yytext());}
"}"                 {return new Symbol(TOK.TK_rbrc,      yychar, yylength(), yytext());}
// Signos
","                 {return new Symbol(TOK.TK_comma,     yychar, yylength(), yytext());}
":"                 {return new Symbol(TOK.TK_colon,     yychar, yylength(), yytext());}
";"                 {return new Symbol(TOK.TK_semicolon, yychar, yylength(), yytext());}
"?"                 {return new Symbol(TOK.TK_question,  yychar, yylength(), yytext());}
//
\n                  {}
{UNUSED}            {}
{COMMENTS}          {painter.COMMENT(yychar, yylength());}
{COMMENTM}          {painter.COMMENT(yychar, yylength());}
.                   {painter.ERROR(yychar, yylength());}