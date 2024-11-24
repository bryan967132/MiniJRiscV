# MiniJRiscV
## Generador de Código Risc-V: JFlex y CUP
Versión minimizada de c++.  
Implementa patrón de diseño interpreter.  
Genera código Risc-V.  
Simula heap con etiquetas.

## VSCode Theme
![Window](Images/ScreenVSCodeTheme.png)

## GitHub Theme
![Window](Images/ScreenGitHubTheme.png)

## Eclipse Theme
![Window](Images/ScreenEclipseTheme.png)

## Gramática Libre del Contexto
```html
<INIT> ::=
    <INSTSGLOBAL>

<INSTSGLOBAL> ::=
    <INSTSGLOBAL> <INSTGLOBAL> |
    <INSTGLOBAL>

<INSTGLOBAL> ::=
    <CALLMAINFUNC> |
    <INITVAR>      |
    <DECLFUNC>

<CALLMAINFUNC> ::=
    'main' <CALLFUNC> ';'

<INITVAR> ::=
    <TYPE> <INITIDS> ';'

<INITIDS> ::=
    <INITIDS> ',' <INITID> |
    <INITID>

<INITID> ::=
    TK_id '=' <EXP> |
    TK_id

<DECLFUNC> ::=
    <TYPE> TK_id '(' <LISTPARAMS> ')' <ENV> |
    'void' TK_id '(' <LISTPARAMS> ')' <ENV> |
    <TYPE> TK_id '(' ')' <ENV>              |
    'void' TK_id '(' ')' <ENV>

<LISTPARAMS> ::=
    <LISTPARAMS> ',' <TYPE> TK_id |
    <TYPE> TK_id

<IF> ::=
    'if' '(' <EXP> ')' <ENV> 'else' <IF>  |
    'if' '(' <EXP> ')' <ENV> 'else' <ENV> |
    'if' '(' <EXP> ')' <ENV>

<SWITCH> ::=
    'switch' '(' <EXP> ')' <ENVS>

<ENVS> ::=
    '{' <CASESDEFAULT> '}' |
    '{' '}'

<CASESDEFAULT> ::=
    <CASES> <DEFAULT> |
    <CASES>           |
    <DEFAULT>

<CASES> ::= 
    <CASES> <CASE> |
    <CASE>

<CASE> ::= 
    'case' <EXP> ':' <INSTRUCTIONS> |
    'case' <EXP> ':' <ENV>          |
    'case' <EXP> ':'

<DEFAULT> ::=
    'default' ':' <INSTRUCTIONS> |
    'default' ':' <ENV>          |
    'default' ':'

<FOR> ::=
    'for' '(' <ARGSFOR> ')' <ENV>

<ARGSFOR> ::=
    <INITIALIZEFOR> ';' <EXP> ';' <UPDATESFOR> |
    <INITIALIZEFOR> ';' <EXP> ';'              |
    <INITIALIZEFOR> ';' ';' <UPDATESFOR>       |
    ';' <EXP> ';' <UPDATESFOR>                 |
    <INITIALIZEFOR> ';' ';'                    |
    ';' <EXP> ';'                              |
    ';' ';' <UPDATESFOR>                       |
    ';' ';'

<INITIALIZEFOR> ::=
    <TYPE> <INITIDSFOR> |
    <ASSIGNS>

<INITIDSFOR> ::=
    <INITIDSFOR> ',' <INITIDFOR> |
    <INITIDFOR>

<INITIDFOR> ::=
    TK_id '=' <EXP>

<ASSIGNS> ::=
    <ASSIGNS> ',' <ASSIGN> |
    <ASSIGN>

<UPDATESFOR> ::=
    <UPDATESFOR> ',' <UPDATEFOR> |
    <UPDATEFOR>

<UPDATEFOR> ::=
    <INCDEC> |
    <ASSIGN> |
    <ADDSUB>

<WHILE> ::=
    'while' '(' <EXP> ')' <ENV>

<DOWHILE> ::=
    'do' <ENV> 'while' '(' <EXP> ')' ';'

<ASSIGN> ::=
    TK_id '=' <EXP>

<ADDSUB> ::=
    TK_id '+=' <EXP> |
    TK_id '-=' <EXP>

<PRINT> ::=
    'println' '(' <EXP> ')' ';' |
    'println' '(' ')' ';'       |
    'print'   '(' <EXP> ')' ';'

<ENV> ::=
    '{' <INSTRUCTIONS> '}' |
    '{' '}'

<INSTRUCTIONS> ::=
    <INSTRUCTIONS> <INSTRUCTION> |
    <INSTRUCTION>

<INSTRUCTION> ::=
    <INITVAR>          |
    <IF>               |
    <SWITCH>           |
    <FOR>              |
    <WHILE>            |
    <DOWHILE>          |
    <ASSIGN>       ';' |
    <ADDSUB>       ';' |
    <INCDEC>       ';' |
    <CALLFUNC>     ';' |
    <PRINT>            |
    'return' <EXP> ';' |
    'return'       ';' |
    'continue'     ';' |
    'break'        ';'

<TYPE> ::=
    'String'  |
    'int'     |
    'boolean' |
    'char'    |
    'double'

<EXP> ::=
    <ARITHMETICS> |
    <RELATIONALS> |
    <LOGICS>      |
    <INCDEC>      |
    <CALLFUNC>    |
    <TERNARY>     |
    <CAST>        |
    TK_id         |
    TK_string     |
    TK_char       |
    TK_int        |
    TK_double     |
    'true'        |
    'false'       |
    '(' <EXP> ')'

<ARITHMETICS> ::=
    <EXP> '+' <EXP> |
    <EXP> '-' <EXP> |
    <EXP> '*' <EXP> |
    <EXP> '/' <EXP> |
    <EXP> '^' <EXP> |
    <EXP> '%' <EXP> |
    '-'   <EXP>

<RELATIONALS> ::=
    <EXP> '==' <EXP> |
    <EXP> '!=' <EXP> |
    <EXP> '<=' <EXP> |
    <EXP> '>=' <EXP> |
    <EXP> '<'  <EXP> |
    <EXP> '>'  <EXP>

<LOGICS> ::=
    <EXP> '&&' <EXP> |
    <EXP> '||' <EXP> |
    '!'   <EXP>

<INCDEC> ::=
    TK_id '++' |
    TK_id '--'

<CALLFUNC> ::=
    TK_id '(' <LISTARGS> ')' |
    TK_id '(' ')'

<LISTARGS> ::=
    <LISTARGS> ',' <EXP> |
    <EXP>

<TERNARY> ::=
    <EXP> '?' <EXP> ':' <EXP>

<CAST> ::=
    <TYPE> '(' <EXP> ')'
```

## Precedencia de Operadores
| Nivel | Asociatividad  |              Token                |
|   -   |       -        |                -                  |
|   10  | Izquierda      | ```?``` ```:```                   |
|   9   | Izquierda      | ```\|\|```                        |
|   8   | Izquierda      | ```&&```                          |
|   7   | Derecha        | ```!```                           |
|   6   | Izquierda      | ```==``` ```!=```                 |
|   5   | Izquierda      | ```<=``` ```>=``` ```<``` ```>``` |
|   4   | Izquierda      | ```+``` ```-```                   |
|   3   | Izquierda      | ```*``` ```/``` ```%```           |
|   2   | No Asociativa  | ```^```                           |
|   1   | Derecha        | ```-```                           |
|   0   | Izquierda      | ```++``` ```--```                 |

## Instalación JFlex y CUP
* Descargar la JFlex: [JFlex](https://jflex.de/download.html)
* Descargar la CUP: [CUP](http://www2.cs.tum.edu/projects/cup/)
* Incluir las librerías en el proyecto java.

## Generación de Scanner
```java
import java.io.File;
public class GScanner {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/Language/Scanner.jflex"
            )
        );
    }
}
```

## Generación de Parser
```java
public class GParser {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                new String[] {
                    "-destdir",
                    "src/Language",
                    "-symbols",
                    "TOK",
                    "-parser",
                    "Parser",
                    "src/Language/Parser.cup"
                }
            );
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
```

## Intérprete con JFlex y CUP
Usuario: [brandonT2002](https://github.com/brandonT2002)  
Repositorio: [MiniJ](https://github.com/brandonT2002/MiniJ)

## Intérprete con JavaCC
Usuario: [bryan967132](https://github.com/bryan967132)  
Repositorio: [MiniJInterpreter](https://github.com/bryan967132/MiniJInterpreter)

## Intérprete de Pseudocódigo con JFlex y CUP
Usuario: [bryan967132](https://github.com/bryan967132)  
Repositorio: [PseudoParser](https://github.com/bryan967132/PseudoParser)

## Traductor a C3D con JFlex y CUP
Usuario: [bryan967132](https://github.com/bryan967132)  
Repositorio: [MiniJC3D](https://github.com/bryan967132/MiniJC3D)