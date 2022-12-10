/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en InformÃ tica 
 * Itinerari: IntelÂ·ligÃ¨ncia Artificial i ComputaciÃ³
 *
 * Professor: Pere Palmer
 */
package compilador.lexic;

import java.io.*;
import java_cup.runtime.ComplexSymbolFactory.*;
import compilador.lexic.utils;
import java_cup.sym;


import compilador.sintactic.ParserSym;

%%
/**
* %standalone
**/

/****
 IndicaciÃ³ de quin tipus d'analitzador sintÃ ctic s'utilitzarÃ . En aquest cas 
 es fa Ãºs de Java CUP.
 ****/

%cup 
/*
%standalone
*/
/****
La lÃ­nia anterior Ã©s una alternativa a la indicaciÃ³ element a element:
/**
* %implements java_cup.runtime.Scanner
* %function next_token
* %type java_cup.runtime.Symbol
*/

****/

%public              // Per indicar que la classe Ã©s pÃºblica
%class Scanner       // El nom de la classe

%char
%line
%column

/*
%eofval{
  return symbol(ParserSym.EOF);
%eofval}
*/
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

// El segÃ¼ent codi es copiarÃ  tambÃ©, dins de la classe. Ã‰s a dir, si es posa res
// ha de ser en el format adient: mÃ¨todes, atributs, etc. 
%{

    utils u = new utils();

    /***
       Mecanismes de gestiÃ³ de sÃ­mbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no Ã©s del tot necessari.
     ***/
    /**
     ConstrucciÃ³ d'un symbol sense atribut associat.
     **/
/*
    private ComplexSymbol symbol(int type) {
        Location l = new Location(yyline+1, yycolumn+1); // primera posiciÃ³ del token
        Location r = new Location(yyline+1, yycolumn+1+yylength()); // ultima posiciÃ³ del token
        ComplexSymbol val = new ComplexSymbol(ParserSym.terminalNames[type], type, l, r);
        return new ComplexSymbol(ParserSym.terminalNames[type], type, l, r, val);
    }


    /**
     ConstrucciÃ³ d'un symbol amb un atribut associat.
     **/

/*
    private ComplexSymbol symbol(int type, Object value){
        Location l = new Location(yyline+1, yycolumn+1); // primera posiciÃ³ del token
        Location r = new Location(yyline+1, yycolumn+1+yylength()); // ultima posiciÃ³ del token
        return new ComplexSymbol(ParserSym.terminalNames[type], type, l, r, value);
    }
*/
%}

/****************************************************************************/
%%

// Regles/accions
// Ã‰s molt important l'ordre de les regles!!!

{op_add}                    { return symbol(ParserSym.op_add); }
{op_sub}                    { return symbol(ParserSym.op_sub); }
{op_mul}                    { return symbol(ParserSym.op_mul); }
{op_div}                    { return symbol(ParserSym.op_div); }
{op_mod}                    { return symbol(ParserSym.op_mod); }

{op_increment}              { return symbol(ParserSym.op_increment); }
{op_decrement}              { return symbol(ParserSym.op_decrement); }

{sym_eq}                    { return symbol(ParserSym.sym_eq); }
{sym_lparen}                { return symbol(ParserSym.sym_lparen); }
{sym_rparen}                { return symbol(ParserSym.sym_rparen); }
{sym_dot}                   { return symbol(ParserSym.sym_dot); }
{sym_colon}                 { return symbol(ParserSym.sym_colon); }
{sym_semicolon}             { return symbol(ParserSym.sym_semicolon); }
{sym_comma}                 { return symbol(ParserSym.sym_comma); }
{sym_lbracket}              { return symbol(ParserSym.sym_lbracket); }
{sym_rbracket}              { return symbol(ParserSym.sym_rbracket); }
{sym_lcbracket}             { return symbol(ParserSym.sym_lcbracket); }
{sym_rcbracket}             { return symbol(ParserSym.sym_rcbracket); }

{rel_eq}                    { return symbol(ParserSym.rel_eq); }
{rel_neq}                   { return symbol(ParserSym.rel_neq); }
{rel_lt}                    { return symbol(ParserSym.rel_lt); }
{rel_le}                    { return symbol(ParserSym.rel_le); }
{rel_gt}                    { return symbol(ParserSym.rel_gt); }
{rel_ge}                    { return symbol(ParserSym.rel_ge); }

{r_if}                      { return symbol(ParserSym.r_if); }
{r_elif}                    { return symbol(ParserSym.r_elif); }
{r_else}                    { return symbol(ParserSym.r_else); }
{r_print}                   { return symbol(ParserSym.r_print); }
{r_println}                 { return symbol(ParserSym.r_println); }
{r_true}                    { return symbol(ParserSym.r_true, new LiteralWrapper(this.yytext(), this.yyline+1, this.yycolumn+1)); }
{r_false}                   { return symbol(ParserSym.r_false, new LiteralWrapper(this.yytext(), this.yyline+1, this.yycolumn+1)); }
{r_function}                { return symbol(ParserSym.r_function); }
{r_procedure}               { return symbol(ParserSym.r_procedure); }
{r_return}                  { return symbol(ParserSym.r_return); }
{r_while}                   { return symbol(ParserSym.r_while); }
{r_for}                     { return symbol(ParserSym.r_for); }
{r_repeat}                  { return symbol(ParserSym.r_repeat); }
{r_until}                   { return symbol(ParserSym.r_until); }
{r_array}                   { return symbol(ParserSym.r_array); }
{r_const}                   { return symbol(ParserSym.r_const); }
{r_and}                     { return symbol(ParserSym.r_and); }
{r_or}                      { return symbol(ParserSym.r_or); }
{r_not}                     { return symbol(ParserSym.r_not); }
{r_new}                     { return symbol(ParserSym.r_new); }
{r_main}                    { return symbol(ParserSym.r_main); }
{r_read}                    { return symbol(ParserSym.r_read); }

{r_int}                     { return symbol(ParserSym.r_int); }
{r_bool}                    { return symbol(ParserSym.r_bool); }
{r_char}                    { return symbol(ParserSym.r_char); }
{r_string}                  { return symbol(ParserSym.r_string); }
{r_tupel}                   { return symbol(ParserSym.r_tupel); }

{id}                        { return symbol(ParserSym.identifier, new LiteralWrapper (this.yytext(), this.yyline+1, this.yycolumn+1)); }

{zerodigit}                 { return symbol(ParserSym.l_int, new LiteralWrapper (0, this.yyline+1, this.yycolumn+1)); }
{enter}                     { return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext()), this.yyline+1, this.yycolumn+1)); }
{tagbinari}{binari}         { return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext().substring(2, this.yytext().length()),2), this.yyline+1, this.yycolumn+1)); }
{taghexa}{hexadecimal}      { return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext().substring(2, this.yytext().length()),16), this.yyline+1, this.yycolumn+1)); }
{tagoctal}{octal}           { return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext().substring(2, this.yytext().length()),8), this.yyline+1, this.yycolumn+1)); }

{singleLnComment}           {}
{multiLnComment}            {}

{l_char}                    { return symbol(ParserSym.l_char, new LiteralWrapper (this.yytext(), this.yyline+1, this.yycolumn+1)); }
{l_string}                  { return symbol(ParserSym.l_string, new LiteralWrapper (this.yytext(), this.yyline+1, this.yycolumn+1)); }

{ws}                        {}
{newline}                   {}

[^]                         { return symbol(ParserSym.error); }

/****************************************************************************/