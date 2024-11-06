/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import Classes.Utils.TypeError;
import Classes.Utils.Outs;

@SuppressWarnings("unused")

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    public Scanner(String input) {
        yyline = 1;
        yychar = 1;
        this.zzReader = new java.io.BufferedReader(
            new java.io.StringReader(input)
        );
    }
%}

//Directivas
%class Scanner
%public
%cupsym TOK
%cup
%char
%full
%line
%unicode

//Constructor
%init{
    yyline = 1;
    yychar = 1;
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
"main"              {return new Symbol(TOK.RW_main,      yyline, yychar, yytext());}
"void"              {return new Symbol(TOK.RW_void,      yyline, yychar, yytext());}
"String"            {return new Symbol(TOK.RW_String,    yyline, yychar, yytext());}
"boolean"           {return new Symbol(TOK.RW_boolean,   yyline, yychar, yytext());}
"char"              {return new Symbol(TOK.RW_char,      yyline, yychar, yytext());}
"int"               {return new Symbol(TOK.RW_int,       yyline, yychar, yytext());}
"double"            {return new Symbol(TOK.RW_double,    yyline, yychar, yytext());}
"if"                {return new Symbol(TOK.RW_if,        yyline, yychar, yytext());}
"else"              {return new Symbol(TOK.RW_else,      yyline, yychar, yytext());}
"for"               {return new Symbol(TOK.RW_for,       yyline, yychar, yytext());}
"while"             {return new Symbol(TOK.RW_while,     yyline, yychar, yytext());}
"do"                {return new Symbol(TOK.RW_do,        yyline, yychar, yytext());}
"switch"            {return new Symbol(TOK.RW_switch,    yyline, yychar, yytext());}
"case"              {return new Symbol(TOK.RW_case,      yyline, yychar, yytext());}
"default"           {return new Symbol(TOK.RW_default,   yyline, yychar, yytext());}
"break"             {return new Symbol(TOK.RW_break,     yyline, yychar, yytext());}
"continue"          {return new Symbol(TOK.RW_continue,  yyline, yychar, yytext());}
"return"            {return new Symbol(TOK.RW_return,    yyline, yychar, yytext());}
"true"              {return new Symbol(TOK.RW_true,      yyline, yychar, yytext());}
"false"             {return new Symbol(TOK.RW_false,     yyline, yychar, yytext());}
"print"             {return new Symbol(TOK.RW_print,     yyline, yychar, yytext());}
// Valores
{CHAR}              {return new Symbol(TOK.TK_char,      yyline, yychar, yytext().substring(1, yylength() - 1));}
{STRING}            {return new Symbol(TOK.TK_string,    yyline, yychar, yytext().substring(1, yylength() - 1));}
{INTEGER}           {return new Symbol(TOK.TK_int,       yyline, yychar, yytext());}
{DOUBLE}            {return new Symbol(TOK.TK_double,    yyline, yychar, yytext());}
// Variables
{ID}                {return new Symbol(TOK.TK_id,        yyline, yychar, yytext());}
// Incremento / Decremento
"++"                {return new Symbol(TOK.TK_inc,       yyline, yychar, yytext());}
"--"                {return new Symbol(TOK.TK_dec,       yyline, yychar, yytext());}
// Concatenacion
"+="                {return new Symbol(TOK.TK_add,       yyline, yychar, yytext());}
"-="                {return new Symbol(TOK.TK_sub,       yyline, yychar, yytext());}
// Operadores Aritméticos
"+"                 {return new Symbol(TOK.TK_plus,      yyline, yychar, yytext());}
"-"                 {return new Symbol(TOK.TK_minus,     yyline, yychar, yytext());}
"*"                 {return new Symbol(TOK.TK_mult,      yyline, yychar, yytext());}
"/"                 {return new Symbol(TOK.TK_div,       yyline, yychar, yytext());}
"^"                 {return new Symbol(TOK.TK_pow,       yyline, yychar, yytext());}
"%"                 {return new Symbol(TOK.TK_mod,       yyline, yychar, yytext());}
// Operadores Relacionales
"=="                {return new Symbol(TOK.TK_equequ,    yyline, yychar, yytext());}
"!="                {return new Symbol(TOK.TK_notequ,    yyline, yychar, yytext());}
"<="                {return new Symbol(TOK.TK_lessequ,   yyline, yychar, yytext());}
">="                {return new Symbol(TOK.TK_moreequ,   yyline, yychar, yytext());}
"="                 {return new Symbol(TOK.TK_equ,       yyline, yychar, yytext());}
"<"                 {return new Symbol(TOK.TK_less,      yyline, yychar, yytext());}
">"                 {return new Symbol(TOK.TK_more,      yyline, yychar, yytext());}
// Operadores Lógicos
"&&"                {return new Symbol(TOK.TK_and,       yyline, yychar, yytext());}
"||"                {return new Symbol(TOK.TK_or,        yyline, yychar, yytext());}
"!"                 {return new Symbol(TOK.TK_not,       yyline, yychar, yytext());}
// Símbolos de Agrupación
"("                 {return new Symbol(TOK.TK_lpar,      yyline, yychar, yytext());}
")"                 {return new Symbol(TOK.TK_rpar,      yyline, yychar, yytext());}
"{"                 {return new Symbol(TOK.TK_lbrc,      yyline, yychar, yytext());}
"}"                 {return new Symbol(TOK.TK_rbrc,      yyline, yychar, yytext());}
// Signos
","                 {return new Symbol(TOK.TK_comma,     yyline, yychar, yytext());}
":"                 {return new Symbol(TOK.TK_colon,     yyline, yychar, yytext());}
";"                 {return new Symbol(TOK.TK_semicolon, yyline, yychar, yytext());}
"?"                 {return new Symbol(TOK.TK_question,  yyline, yychar, yytext());}
//
\n                  {yychar = 1;}
{UNUSED}            {}
{COMMENTS}          {}
{COMMENTM}          {}
.                   {Outs.errors.add(Outs.newError(yyline, yychar, TypeError.LEXICAL, "Caracter no reconocido. «" + yytext() + "»"));}