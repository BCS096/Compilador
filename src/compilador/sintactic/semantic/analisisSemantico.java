/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.semantic;

import compilador.sintactic.Parser;
import compilador.sintactic.ParserSym;
import compilador.sintactic.nodes.*;
import tablas.ConstDescripcion;
import tablas.Data;
import tablas.IdDescripcion;
import tablas.ProcDescripcion;
import tablas.TablaSimbolos;
import tablas.TypeDescripcion;
import types.TypeEnum;

/**
 *
 * @author emanu
 */
public class analisisSemantico {

    private final Parser parser;
    private final ProgramNode programNode;

    private final TablaSimbolos ts;
    //private final CodeGeneration3Address codeGeneration3Address;

    public analisisSemantico(ProgramNode program, Parser parser) {
        this.programNode = program;
        this.parser = parser;
        this.ts = new TablaSimbolos();
        //this.codeGeneration3Address = new CodeGeneration3Address(variableTable, procedureTable);

        initTablaSimbolos();

    }

    private void initTablaSimbolos() {
        /*
        IdDescripcion nulo = new IdDescripcion(IdDescripcion.TipoDescripcion.dnula);
        ts.poner("if", nulo);
        ts.poner("elif", nulo);
        ts.poner("else", nulo);
        ts.poner("while", nulo);
        ts.poner("repeat", nulo);
        ts.poner("until", nulo);
        ts.poner("for", nulo);
        ts.poner("print", nulo);
        ts.poner("println", nulo);
        ts.poner("read", nulo);
        ts.poner("procedure", nulo);
        ts.poner("return", nulo);
        ts.poner("function", nulo);
        ts.poner("new", nulo);
        ts.poner("const", nulo);
        ts.poner("array", nulo);
        ts.poner("tupel", nulo);
        */
        TypeDescripcion tipo = new TypeDescripcion(TypeDescripcion.TSB.tsb_int, Integer.MIN_VALUE, Integer.MAX_VALUE);
        ts.poner("int", tipo);
        tipo = new TypeDescripcion(TypeDescripcion.TSB.tsb_char, Character.MIN_VALUE, Character.MAX_VALUE);
        ts.poner("char", tipo);
        tipo = new TypeDescripcion(TypeDescripcion.TSB.tsb_boolean, 0, 1);
        ts.poner("boolean", tipo);

        ConstDescripcion constDesc = new ConstDescripcion(0, TypeEnum.BOOL, true);
        ts.poner("false", constDesc);
        constDesc = new ConstDescripcion(1, TypeEnum.BOOL, true);
        ts.poner("true", constDesc);

        /* Para que las palabras reservadas queden en el ámbito 1 de forma exclusiva */
        ts.entrarBloque();
        ProcDescripcion mainDescription = new ProcDescripcion(-1);
        ts.poner("main", mainDescription);
    }

    public void handleProgram() {
        MainNode main = programNode.getMain();
        if (main != null) {
            DeclListNode declList = programNode.getDeclList();
            if (declList != null && !declList.isEmpty()) {
                System.out.println("AVANZANDO CHAVALES");
            }else{
                System.out.println("La declList está vacía pero avanzando igual jeje");
            }
        }
    }
    
/**   
 * @Manu
PROGRAM;
DECL_LIST;
DECL;
ACTUAL_DECL;
DECL_ELEM;
TYPE_ID;
ELEM_LIST;
ELEM_ID_ASSIG;
DECL_ARRAY;
DIM_ARRAY;
ARRAY_DECL;
INIT_ARRAY;
DECL_TUPEL;
TUPEL_DECL;
INIT_TUPEL;
EXP;
* @Coti
METHOD_LIST;
METHOD;
PROC;
FUNC;
PARAM_LIST;
ACTUAL_PARAM_LIST;
PARAM;
SPECIAL_PARAM; 
SENTENCE_LIST;
SENTENCE;
FOR_INST;
NEXT_IF;
INST;
INST_EXP;
METHOD_CALL;
* @Constantino
PARAM_IN;
ASSIG;
SIMPLE_VALUE;
GEST_IDX;
GESTOR;
LITERAL;
BINARY_OP;
REL_OP;
LOGIC_OP;
ARIT_OP;
NEG_OP;
SPECIAL_OP;
MAIN;
MODIFIER;
ID;
*/
}
