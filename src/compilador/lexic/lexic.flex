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
import compilador.main.MVP;


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

/****
La lÃ­nia anterior Ã©s una alternativa a la indicaciÃ³ element a element:

%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol

****/

%public              // Per indicar que la classe Ã©s pÃºblica
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

// El segÃ¼ent codi es copiarÃ  tambÃ©, dins de la classe. Ã‰s a dir, si es posa res
// ha de ser en el format adient: mÃ¨todes, atributs, etc. 
%{
    MVP mvp;

    public Scanner(java.io.Reader in, MVP mvp) {
    this.zzReader = in;
    this.mvp = mvp;
  }

    utils u = new utils();

    /***
       Mecanismes de gestiÃ³ de sÃ­mbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no Ã©s del tot necessari.
     ***/
    /**
     ConstrucciÃ³ d'un symbol sense atribut associat.
     **/

    private ComplexSymbol symbol(int type) {
        Location l = new Location(yyline+1, yycolumn+1); // primera posiciÃ³ del token
        Location r = new Location(yyline+1, yycolumn+1+yylength()); // ultima posiciÃ³ del token
        ComplexSymbol val = new ComplexSymbol(ParserSym.terminalNames[type], type, l, r);
        return new ComplexSymbol(ParserSym.terminalNames[type], type, l, r, val);
    }


    /**
     ConstrucciÃ³ d'un symbol amb un atribut associat.
     **/


    private ComplexSymbol symbol(int type, Object value){
        Location l = new Location(yyline+1, yycolumn+1); // primera posiciÃ³ del token
        Location r = new Location(yyline+1, yycolumn+1+yylength()); // ultima posiciÃ³ del token
        return new ComplexSymbol(ParserSym.terminalNames[type], type, l, r, value);
    }

%}

/****************************************************************************/
%%

// Regles/accions
// Ã‰s molt important l'ordre de les regles!!!

{op_add}                    { mvp.setLexic("Símbol '+' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_add); }
{op_sub}                    { mvp.setLexic("Símbol '-' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_sub); }
{op_mul}                    { mvp.setLexic("Símbol '*' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_mul); }
{op_div}                    { mvp.setLexic("Símbol '/' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_div); }
{op_mod}                    { mvp.setLexic("Símbol '%' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_mod); }

{op_increment}              { mvp.setLexic("Símbol '++' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_increment); }
{op_decrement}              { mvp.setLexic("Símbol '--' -> "+this.yytext()+"\n"); return symbol(ParserSym.op_decrement); }

{sym_eq}                    { mvp.setLexic("Símbol '=' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_eq); }
{sym_lparen}                { mvp.setLexic("Símbol '(' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_lparen); }
{sym_rparen}                { mvp.setLexic("Símbol ')' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_rparen); }
{sym_dot}                   { mvp.setLexic("Símbol '.' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_dot); }
{sym_colon}                 { mvp.setLexic("Símbol ':' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_colon); }
{sym_semicolon}             { mvp.setLexic("Símbol ';' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_semicolon); }
{sym_comma}                 { mvp.setLexic("Símbol ',' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_comma); }
{sym_lbracket}              { mvp.setLexic("Símbol '[' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_lbracket); }
{sym_rbracket}              { mvp.setLexic("Símbol ']' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_rbracket); }
{sym_lcbracket}             { mvp.setLexic("Símbol '{' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_lcbracket); }
{sym_rcbracket}             { mvp.setLexic("Símbol '}' -> "+this.yytext()+"\n"); return symbol(ParserSym.sym_rcbracket); }

{rel_eq}                    { mvp.setLexic("Símbol '=' -> "+this.yytext()+"\n"); return symbol(ParserSym.rel_eq); }
{rel_neq}                   { mvp.setLexic("Símbol '!=' -> "+this.yytext()+"\n"); return symbol(ParserSym.rel_neq); }
{rel_lt}                    { mvp.setLexic("Símbol '<' -> "+this.yytext()+"\n"); return symbol(ParserSym.rel_lt); }
{rel_le}                    { mvp.setLexic("Símbol '<=' -> "+this.yytext()+"\n"); return symbol(ParserSym.rel_le); }
{rel_gt}                    { mvp.setLexic("Símbol '>' -> "+this.yytext()+"\n"); return symbol(ParserSym.rel_gt); }
{rel_ge}                    { mvp.setLexic("Símbol '>=' -> "+this.yytext()+"\n"); return symbol(ParserSym.rel_ge); }

{r_if}                      { mvp.setLexic("Símbol 'if' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_if); }
{r_elif}                    { mvp.setLexic("Símbol 'elif' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_elif); }
{r_else}                    { mvp.setLexic("Símbol 'else' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_else); }
{r_print}                   { mvp.setLexic("Símbol 'print()' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_print); }
{r_println}                 { mvp.setLexic("Símbol 'println()' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_println); }
{r_true}                    { mvp.setLexic("Símbol 'true' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_true, new LiteralWrapper(this.yytext(), this.yyline+1, this.yycolumn+1)); }
{r_false}                   { mvp.setLexic("Símbol 'false' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_false, new LiteralWrapper(this.yytext(), this.yyline+1, this.yycolumn+1)); }
{r_function}                { mvp.setLexic("Símbol 'function(){}' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_function); }
{r_procedure}               { mvp.setLexic("Símbol 'procedure(){}' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_procedure); }
{r_return}                  { mvp.setLexic("Símbol 'return' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_return); }
{r_while}                   { mvp.setLexic("Símbol 'while' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_while); }
{r_for}                     { mvp.setLexic("Símbol 'for' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_for); }
{r_repeat}                  { mvp.setLexic("Símbol 'repeat' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_repeat); }
{r_until}                   { mvp.setLexic("Símbol 'until' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_until); }
{r_array}                   { mvp.setLexic("Símbol 'array' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_array); }
{r_const}                   { mvp.setLexic("Símbol 'const' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_const); }
{r_and}                     { mvp.setLexic("Símbol 'and' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_and); }
{r_or}                      { mvp.setLexic("Símbol 'or' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_or); }
{r_not}                     { mvp.setLexic("Símbol 'not' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_not); }
{r_new}                     { mvp.setLexic("Símbol 'new' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_new); }
{r_main}                    { mvp.setLexic("Símbol 'main(){}' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_main); }
{r_read}                    { mvp.setLexic("Símbol 'read()' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_read); }

{r_int}                     { mvp.setLexic("Símbol 'int' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_int); }
{r_bool}                    { mvp.setLexic("Símbol 'bool' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_bool); }
{r_char}                    { mvp.setLexic("Símbol 'char' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_char); }
{r_string}                  { mvp.setLexic("Símbol 'string' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_string); }
{r_tupel}                   { mvp.setLexic("Símbol 'tupel' -> "+this.yytext()+"\n"); return symbol(ParserSym.r_tupel); }

{id}                        { mvp.setLexic("Símbol 'ID' -> "+this.yytext()+"\n"); return symbol(ParserSym.identifier, new LiteralWrapper (this.yytext(), this.yyline+1, this.yycolumn+1)); }

{zerodigit}                 { mvp.setLexic("Literal 0 -> "+this.yytext()+"\n"); return symbol(ParserSym.l_int, new LiteralWrapper (0, this.yyline+1, this.yycolumn+1)); }
{enter}                     { mvp.setLexic("Literal integer -> "+this.yytext()+"\n"); return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext()), this.yyline+1, this.yycolumn+1)); }
{tagbinari}{binari}         { mvp.setLexic("Literal binari -> "+this.yytext()+"\n"); return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext().substring(2, this.yytext().length()),2), this.yyline+1, this.yycolumn+1)); }
{taghexa}{hexadecimal}      { mvp.setLexic("Literal hexadecimal -> "+this.yytext()+"\n"); return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext().substring(2, this.yytext().length()),16), this.yyline+1, this.yycolumn+1)); }
{tagoctal}{octal}           { mvp.setLexic("Literal octal -> "+this.yytext()+"\n"); return symbol(ParserSym.l_int, new LiteralWrapper (Integer.parseInt(this.yytext().substring(2, this.yytext().length()),8), this.yyline+1, this.yycolumn+1)); }

{singleLnComment}           { mvp.setLexic("Símbol '//' -> "+this.yytext()+"\n"); }
{multiLnComment}            { mvp.setLexic("Símbol '/**/' -> "+this.yytext()+"\n"); }

{l_char}                    { mvp.setLexic("Literal character -> "+this.yytext()+"\n"); return symbol(ParserSym.l_char, new LiteralWrapper (this.yytext(), this.yyline+1, this.yycolumn+1)); }
{l_string}                  { mvp.setLexic("Literal string -> "+this.yytext()+"\n"); return symbol(ParserSym.l_string, new LiteralWrapper (this.yytext(), this.yyline+1, this.yycolumn+1)); }

{ws}                        { mvp.setLexic("White space '}' -> "+this.yytext()+"\n"); }
{newline}                   { mvp.setLexic("New line -> "+this.yytext()+"\n"); }

[^]                         { mvp.setLexic("Símbol desconegut -> "+this.yytext()+", Levenshtein retorna ->" + u.Levenshtein(this.yytext()) + "\n"); return symbol(ParserSym.error); }

/****************************************************************************/