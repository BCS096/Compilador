
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package compilador.sintactic;

/** CUP generated interface containing symbol constants. */
public interface ParserSym {
  /* terminals */
  public static final int r_true = 32;
  public static final int sym_comma = 10;
  public static final int rel_lt = 43;
  public static final int sym_rcbracket = 16;
  public static final int r_return = 31;
  public static final int sym_semicolon = 9;
  public static final int op_sub = 3;
  public static final int r_false = 33;
  public static final int r_procedure = 29;
  public static final int op_increment = 7;
  public static final int l_int = 54;
  public static final int r_print = 50;
  public static final int r_function = 30;
  public static final int r_else = 20;
  public static final int rel_le = 44;
  public static final int r_read = 52;
  public static final int identifier = 53;
  public static final int r_const = 39;
  public static final int op_decrement = 8;
  public static final int sym_colon = 11;
  public static final int r_or = 47;
  public static final int rel_eq = 41;
  public static final int r_string = 38;
  public static final int sym_rbracket = 18;
  public static final int r_array = 25;
  public static final int r_elif = 21;
  public static final int r_if = 19;
  public static final int l_char = 55;
  public static final int EOF = 0;
  public static final int sym_lcbracket = 15;
  public static final int r_int = 35;
  public static final int r_tupel = 26;
  public static final int error = 1;
  public static final int op_div = 5;
  public static final int r_for = 23;
  public static final int r_char = 36;
  public static final int r_not = 49;
  public static final int r_and = 48;
  public static final int r_until = 28;
  public static final int rel_gt = 45;
  public static final int op_mul = 4;
  public static final int op_add = 2;
  public static final int r_println = 51;
  public static final int op_mod = 6;
  public static final int r_repeat = 27;
  public static final int sym_lparen = 13;
  public static final int sym_eq = 34;
  public static final int rel_ge = 46;
  public static final int r_bool = 37;
  public static final int r_new = 24;
  public static final int sym_rparen = 14;
  public static final int sym_dot = 12;
  public static final int r_main = 40;
  public static final int l_string = 56;
  public static final int rel_neq = 42;
  public static final int sym_lbracket = 17;
  public static final int r_while = 22;
  public static final String[] terminalNames = new String[] {
  "EOF",
  "error",
  "op_add",
  "op_sub",
  "op_mul",
  "op_div",
  "op_mod",
  "op_increment",
  "op_decrement",
  "sym_semicolon",
  "sym_comma",
  "sym_colon",
  "sym_dot",
  "sym_lparen",
  "sym_rparen",
  "sym_lcbracket",
  "sym_rcbracket",
  "sym_lbracket",
  "sym_rbracket",
  "r_if",
  "r_else",
  "r_elif",
  "r_while",
  "r_for",
  "r_new",
  "r_array",
  "r_tupel",
  "r_repeat",
  "r_until",
  "r_procedure",
  "r_function",
  "r_return",
  "r_true",
  "r_false",
  "sym_eq",
  "r_int",
  "r_char",
  "r_bool",
  "r_string",
  "r_const",
  "r_main",
  "rel_eq",
  "rel_neq",
  "rel_lt",
  "rel_le",
  "rel_gt",
  "rel_ge",
  "r_or",
  "r_and",
  "r_not",
  "r_print",
  "r_println",
  "r_read",
  "identifier",
  "l_int",
  "l_char",
  "l_string"
  };
}
