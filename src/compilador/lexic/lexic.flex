/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en Informàtica 
 * Itinerari: Intel·ligència Artificial i Computació
 *
 * Professor: Pere Palmer
 */
package compilador.lexic;

import java.io.*;
import java_cup.runtime.ComplexSymbolFactory.*;
import compilador.lexic.utils;


import sintactic.ParserSym;

%%
/**
* %standalone
**/

/****
 Indicació de quin tipus d'analitzador sintàctic s'utilitzarà. En aquest cas 
 es fa ús de Java CUP.
 ****/

%cup 

/****
La línia anterior és una alternativa a la indicació element a element:
/**
* %implements java_cup.runtime.Scanner
* %function next_token
* %type java_cup.runtime.Symbol
*/

****/

%public              // Per indicar que la classe és pública
%class Scanner       // El nom de la classe

%char
%line
%column


%eofval{
  return symbol(ParserSym.EOF);
%eofval}

// Declaracions

id		= [A-Za-z_][A-Za-z0-9_]*
l_char          = "'"[^"'"]"'"
l_string        = "\""[^"\""]*"\""

zerodigit	= 0
tagbinari	= 0b
taghexa		= 0x
tagoctal	= 0o

enter           = 0 | [1-9][0-9]*
binari		= [0-1]+
octal		= [0-7]+
hexadecimal	= [0-9A-Fa-f]+

singleLnComment = "//"([^\n])*
multiLnComment  = "/*"[^"*/"]*"*/"

ws              = [' '|'\t']+
newline         = ['\r'|'\n'|"\r\n"]+

op_add          = \+
op_sub          = \-
op_mul          = \*
op_div          = \/
op_mod          = "mod"

op_increment    = "++"
op_decrement    = "--"

sym_eq          = \=
sym_lparen      = \(
sym_rparen      = \)
sym_dot         = \.
sym_colon       = \:
sym_semicolon   = \;
sym_comma       = \,
sym_lbracket    = \[
sym_rbracket    = \]
sym_lcbracket   = \{
sym_rcbracket   = \}

rel_eq          = "=="
rel_neq         = "!="
rel_lt          = \<
rel_le          = "<="
rel_gt          = \>
rel_ge          = ">="

r_if            = "if"
r_elif          = "elif"
r_else          = "else"
r_print         = "print"
r_println       = "println"
r_read          = "read"
r_true          = "true"
r_false         = "false"
r_function      = "function"
r_procedure     = "procedure"
r_return        = "return"
r_while         = "while"
r_for           = "for"
r_repeat        = "repeat"
r_until         = "until"
r_array         = "array"
r_const         = "const"
r_and           = "and"
r_or            = "or"
r_not           = "not"
r_new           = "new"
r_main          = "main"

r_int           = "int"
r_bool          = "boolean"
r_char          = "char"
r_string        = "string"
r_tupel         = "tupel"

// El següent codi es copiarà també, dins de la classe. És a dir, si es posa res
// ha de ser en el format adient: mètodes, atributs, etc. 
%{

    utils u = new utils();

    /***
       Mecanismes de gestió de símbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     Construcció d'un symbol sense atribut associat.
     **/

    private ComplexSymbol symbol(int type) {
        Location l = new Location(yyline+1, yycolumn+1); // primera posició del token
        Location r = new Location(yyline+1, yycolumn+1+yylength()); // ultima posició del token
        ComplexSymbol val = new ComplexSymbol(ParserSym.terminalNames[type], type, l, r);
        return new ComplexSymbol(ParserSym.terminalNames[type], type, l, r, val);
    }


    /**
     Construcció d'un symbol amb un atribut associat.
     **/


    private ComplexSymbol symbol(int type, Object value){
        Location l = new Location(yyline+1, yycolumn+1); // primera posició del token
        Location r = new Location(yyline+1, yycolumn+1+yylength()); // ultima posició del token
        return new ComplexSymbol(ParserSym.terminalNames[type], type, l, r, value);
    }

%}

/****************************************************************************/
%%

// Regles/accions
// És molt important l'ordre de les regles!!!

{op_add}                    { System.out.println("suma " + this.yytext());}
{op_sub}                    { System.out.println("sub " + this.yytext());}
{op_mul}                    { System.out.println("mul " + this.yytext());}
{op_div}                    { System.out.println("div " + this.yytext());}
{op_mod}                    { System.out.println("mod " + this.yytext());}

{op_increment}              { System.out.println("increment " + this.yytext());}
{op_decrement}              { System.out.println("decrement " + this.yytext());}

{sym_eq}                    { System.out.println("equ = "+this.yytext());}
{sym_lparen}                { System.out.println("lparen "+this.yytext());}
{sym_rparen}                { System.out.println("rparen "+this.yytext());}
{sym_dot}                   { System.out.println("dot "+this.yytext());}
{sym_colon}                 { System.out.println("colon "+this.yytext());}
{sym_semicolon}             { System.out.println("semicolon "+this.yytext());}
{sym_comma}                 { System.out.println("comma "+this.yytext());}
{sym_lbracket}              { System.out.println("lbracket "+this.yytext());}
{sym_rbracket}              { System.out.println("rbracket "+this.yytext());}
{sym_lcbracket}             { System.out.println("lcbracket "+this.yytext());}
{sym_rcbracket}             { System.out.println("rcbracket "+this.yytext());}

{rel_eq}                    { System.out.println("eq == "+this.yytext());}
{rel_neq}                   { System.out.println("neq "+this.yytext());}
{rel_lt}                    { System.out.println("lt "+this.yytext());}
{rel_le}                    { System.out.println("le "+this.yytext());}
{rel_gt}                    { System.out.println("gt "+this.yytext());}
{rel_ge}                    { System.out.println("ge "+this.yytext());}

{r_if}                      { System.out.println("if "+this.yytext());}
{r_elif}                    { System.out.println("elif "+this.yytext());}
{r_else}                    { System.out.println("else "+this.yytext());}
{r_print}                   { System.out.println("print "+this.yytext());}
{r_println}                 { System.out.println("println "+this.yytext());}
{r_true}                    { System.out.println("true "+this.yytext());}
{r_false}                   { System.out.println("false "+this.yytext());}
{r_function}                { System.out.println("function "+this.yytext());}
{r_procedure}               { System.out.println("procedure "+this.yytext());}
{r_return}                  { System.out.println("return "+this.yytext());}
{r_while}                   { System.out.println("while "+this.yytext());}
{r_for}                     { System.out.println("for "+this.yytext());}
{r_repeat}                  { System.out.println("repeat "+this.yytext());}
{r_until}                   { System.out.println("until "+this.yytext());}
{r_array}                   { System.out.println("array "+this.yytext());}
{r_const}                   { System.out.println("const "+this.yytext());}
{r_and}                     { System.out.println("and "+this.yytext());}
{r_or}                      { System.out.println("or "+this.yytext());}
{r_not}                     { System.out.println("not "+this.yytext());}
{r_new}                     { System.out.println("new "+this.yytext());}
{r_main}                    { System.out.println("main "+this.yytext());}
{r_read}                    { System.out.println("read "+this.yytext());}

{r_int}                     { System.out.println("int "+this.yytext());}
{r_bool}                    { System.out.println("bool "+this.yytext());}
{r_char}                    { System.out.println("char "+this.yytext());}
{r_string}                  { System.out.println("string "+this.yytext());}
{r_tupel}                   { System.out.println("tupel "+this.yytext());}

{id}                        { System.out.println("id "+this.yytext());}

{zerodigit}                 { System.out.println("zerodigit "+this.yytext());}
{enter}                     { System.out.println("enter "+this.yytext());}
{tagbinari}{binari}         { System.out.println("binari "+this.yytext());}
{taghexa}{hexadecimal}      { System.out.println("hexadecimal "+this.yytext());}
{tagoctal}{octal}           { System.out.println("octal "+this.yytext());}

{singleLnComment}           { System.out.println("singleLnComment "+this.yytext());}
{multiLnComment}            { System.out.println("multiLnComment "+this.yytext());}

{l_char}                    { System.out.println("char "+this.yytext());}
{l_string}                  { System.out.println("string "+this.yytext());}

{ws}                        { System.out.println("ws "+this.yytext());}
{newline}                   { /* No fer res amb els espais */  }

[^]                         { System.err.println(this.yytext() + ", corregido: " + u.Levenshtein((this.yytext()).toString())); }

/****************************************************************************/