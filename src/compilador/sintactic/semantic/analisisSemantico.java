/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.semantic;

import compilador.sintactic.Parser;
import compilador.sintactic.ParserSym;
import compilador.sintactic.nodes.*;
import tablas.*;
import types.TypeEnum;

/**
 *
 * @author emanu
 */
public class analisisSemantico {

    private final Parser parser;
    private final ProgramNode programNode;

    private final TablaSimbolos ts;
    private final TablaVariables tv;
    private final TablaProcedimientos tp;
    private final CodeGeneration3Address gc;

    public analisisSemantico(ProgramNode program, Parser parser) {
        this.programNode = program;
        this.parser = parser;
        this.ts = new TablaSimbolos();
        this.tv = new TablaVariables();
        this.tp = new TablaProcedimientos();
        this.gc = new CodeGeneration3Address(tv, tp);

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
        //TypeDescripcion no sirve para nada porque ya tenemos TypeEnum, pero diria que lo suyo y lo màs adecuado es usar TypeDescripcion
        //y borrar typeEnum
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
        //coti : no se deberia poner dentro de la tabla de procedimientos y que la tabla de procedimientos te proporciona el número de procedimiento
        // y no un -1 por la cara (y por tanto devolveria 0)????????? Creo que debemos saber en todo momento donde estamos.
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
    // COTI
    public void handleMethodList(MethodListNode node){
        if(node.getMethod() != null){
            handleMethod(node.getMethod());
        }
        if(node.getMethodList() != null && !(node.getMethodList().isEmpty()) ){
            handleMethodList(node.getMethodList());
        }
    }
    
    public void handleMethod(MethodNode node){
        if(node.getProc() != null){
            handleProc(node.getProc());
        }
        if(node.getFunc() != null){
            handleFunc(node.getFunc());
        }
    }
    
    public void handleProc(ProcNode node){
        
    }
    
    public void handleFunc(FuncNode node){
        
    }
    
/**   
 * @Manu
PROGRAM;
DECL_LIST;
DECL;
ACTUAL_DECL;
DECL_ELEM;
DECL_ARRAY;
DIM_ARRAY;
ARRAY_DECL;
INIT_ARRAY;
DECL_TUPEL;
TUPEL_DECL;
INIT_TUPEL;
EXP;
SIMPLE_VALUE;
GEST_IDX;
GESTOR;
* @Coti
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
TYPE_ID;
ELEM_LIST;
ELEM_ID_ASSIG;
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
    
    //DEADLINE: 14-01-2023 -> Tenerlos hechos (no hace falta que bien), quedar y arreglarlos (si hace falta).
}
