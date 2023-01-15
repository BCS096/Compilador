/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.semantic;

import compilador.sintactic.Parser;
import compilador.sintactic.ParserSym;
import compilador.sintactic.nodes.*;
import compilador.sintactic.semantic.Operator3Address.CastType;

import java.util.ArrayList;
import tablas.*;
import tablas.IdDescripcion.TipoDescripcion;
import types.SentenceType;
import types.SpecialOpType;
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
        
        ProcDescripcion mainDescription = new ProcDescripcion(-1);
        
        ts.poner("main", mainDescription);
    }

    public void handleProgram() {
        MainNode main = programNode.getMain();
        if (main != null) {
            DeclListNode declList = programNode.getDeclList();
            if (declList != null && !declList.isEmpty()) {
                handleDeclList(declList);
            }

            MethodListNode methodList = programNode.getMethodList();
            if (methodList != null && !methodList.isEmpty()) {
                handleMethodList(methodList);
            }

            CG3A.generate(InstructionType.CALL, new Operator3Address("main"), null, null);
            handleMain(main);
        } else {
            parser.report_error("ERROR - No se ha declarado 'main'", programNode);
        }
        StringBuilder printCG3A = new StringBuilder();
        printCG3A.append("instructionType |            operator1             |           operator2           |          result            ");
        CG3A.getInstruccions().forEach(ins -> {
            printCG3A.append(ins);
        });
    }

    public void handleDeclList(DeclListNode declList) {
        DeclListNode listaDecls = declList.getDeclList();
        //Lista con declaraciones?
        if (listaDecls != null && !listaDecls.isEmpty()) {
            handleDeclList(listaDecls);
        }
        //Una sola declaración
        handleDecl(declList.getDecl());
    }

    public void handleDecl(DeclNode decl) {
        ModifierNode mod = decl.getModifier();
        if (mod == null) {
            mod = new ModifierNode(IdDescripcion.TipoDescripcion.dvar, false, 0, 0);
        }

        handleActualDecl(decl.getActualDecl(), mod.getDescriptionType());
    }

    public void handleActualDecl(ActualDeclNode actualDecl, IdDescripcion.TipoDescripcion modifier) {
        DeclElemNode declElem = actualDecl.getDeclElem();
        DeclArrayNode declArray = actualDecl.getDeclArray();
        DeclTupelNode declTupel = actualDecl.getDeclTupel();
        //Tenemos elementos
        if (declElem != null) {
            handleDeclElem(declElem, modifier);
            //O bien tenemos array
        } else if (declArray != null) {
            handleDeclArray(declArray, modifier);
            //O bien tuplas
        } else if (declTupel != null) {
            handleDeclTupel(declTupel, modifier);
            //Si no es ninguna, error
        } else {
            parser.report_error("Actual decl no contiene elementos, arrays o tuplas!", actualDecl);
        }
    }

    public void handleDeclElem(DeclElemNode declElem, IdDescripcion.TipoDescripcion modifier) {
        TypeEnum type = declElem.getTypeId().getType();
        //Una lista de elementos
        handleElemList(declElem.getElemList(), modifier, type);
    }

    public void handleElemList(ElemListNode elemList, IdDescripcion.TipoDescripcion modifier, TypeEnum type) {
        ElemListNode listaElementos = elemList.getElemList();
        if (listaElementos != null) {
            handleElemList(listaElementos, modifier, type);
        }
        //Es un solo elemento, o final de lista
        handleElemIdAssig(elemList.getElemIdAssig(), modifier, type);
    }

    public void handleElemIdAssig(ElemIdAssigNode elemIdAssig, IdDescripcion.TipoDescripcion modifier, TypeEnum type) {
        String id = (String) elemIdAssig.getIdentifier().getIdentifierLiteral();
        ExpressionNode expression = elemIdAssig.getExp();
        int expResultVarNumber = -9;
        if (expression != null) {
            handleExpression(expression);
            expResultVarNumber = expression.getReference();
            if (type != expression.getType()) {
                parser.report_error("Identificador no coincide con la exp", expression);
            }
        }

        int nVar;
        switch (modifier) {
            case dvar:
                if (type != TypeEnum.STRING) {
                    nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
                    VarDescripcion var = new VarDescripcion(nVar, type);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Variable " + id + " ya se ha definido", elemIdAssig);
                        tv.decrement();
                    }
                } else {
                    nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
                    StringDescripcion var = new StringDescripcion(nVar, true, expression != null);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Variable " + id + " ya se ha definido", elemIdAssig);
                        tv.decrement();
                    }
                }

                // If expression given, make the assignation
                if (expResultVarNumber != -9) {
                    CG3A.generate(InstructionType.CLONE, new Operator3Address(expResultVarNumber), null, new Operator3Address(nVar));
                }
                break;
            case dconst:
                if (type != TypeEnum.STRING) {
                    nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
                    ConstDescripcion constant = new ConstDescripcion(nVar, type, expression != null);
                    try {
                        ts.poner(id, constant);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Constante " + id + " ya está definida", elemIdAssig);
                        tv.decrement();
                    }
                } else {
                    nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
                    StringDescripcion var = new StringDescripcion(nVar, false, expression != null);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Constant " + id + " is already defined", elemIdAssig);
                        tv.decrement();
                    }
                }

                // If expression given, make the assignation
                if (expResultVarNumber != -9) {
                    CG3A.generate(InstructionType.CLONE, new Operator3Address(expResultVarNumber), null, new Operator3Address(nVar));
                }
                break;
            default:
                break;
        }
    }

    public void handleDeclArray(DeclArrayNode declArray, IdDescripcion.TipoDescripcion modifier) {
        TypeEnum tipo = declArray.getTypeId().getType();
        String id = (String) declArray.getIdentifier().getIdentifierLiteral();

        ArrayDeclNode init = declArray.getArrayDecl();
        Boolean initialized = (init != null && !init.isEmpty());
        ArrayDescripcion arr;
        switch (modifier) {
            case dvar:
                int nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, tipo, true, false);

                arr = new ArrayDescripcion(nVar, tipo, true, initialized);
                try {
                    ts.poner(id, arr);
                } catch (IllegalArgumentException e) {
                    parser.report_error("Variable " + id + " ya está definida", declArray);
                }
                break;
            case dconst:
                nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, tipo, true, false);

                arr = new ArrayDescripcion(nVar, tipo, false, initialized);
                try {
                    ts.poner(id, arr);
                } catch (IllegalArgumentException e) {
                    parser.report_error("Constante " + id + " ya está definida", declArray);
                }
                break;
            default:
                throw new RuntimeException("No es una constante ni una variable!");
        }

        if (initialized) {
            //Caso de que ya esté inicializado (tiene su declaración y eso significa que si)
            handleInitArray(declArray.getArrayDecl().getInitArrayNode());
        }
    }
    
    public void handleDimArray(DimArrayNode dimArray, ExpressionNode exp){
        //TODO: Poner []? en CG3A me refiero, por discutir
        if(dimArray.getNextDim() != null && !dimArray.getNextDim().isEmpty()){
            handleDimArray(dimArray.getNextDim(), dimArray.getDim());
        }
        
        if(dimArray.getDim() != null){
            handleExpression(dimArray.getDim());
        }
    }

    public void handleInitArray(InitArrayNode initArray) {
        TypeEnum assigType = initArray.getTypeId().getType();
        //Comprobación de tipo ya no es posible, no está en la producción. Pasarla como parámetro de alguna manera?????

        if (initArray.getDimArray() != null) {
            handleDimArray(initArray.getDimArray());
        } else {
            parser.report_error("No se ha encontrado la dimensión del array!", initArray);
        }
    }

    public void handleArrayDecl(InitArrayNode initArray, TypeEnum type, ArrayDescripcion arrayDesc) {
        if (!initArray.isEmpty()) {
            handleInitArray(initArray);
        }
    }

    public void handleExpression(ExpressionNode expressionNode) {
        if (expressionNode != null) {
            //Si tenemos alguna expresion en el lado izquierdo, pero no en el derecho
            if (expressionNode.getExp1() != null && expressionNode.getExp2() == null) {
                //Analizamos la expresion (analizaremos hacia la izquierda y abajo todo lo posible primero)
                handleExpression(expressionNode.getExp1());
                //Si tenemos un operador de negacion ("not")
                if (expressionNode.getNegOp() != null) {
                    //Si queremos negar, tendremos que negar un booleano, no puede ser otra cosa
                    if (expressionNode.getExp1().getType() != TypeEnum.BOOL) {
                        parser.report_error("Trying to apply not to a non boolean value, got type: " + expressionNode.getExp1().getType(), expressionNode.getNegOp());
                    }
                    //Entonces nuestra expresion será booleana
                    expressionNode.setType(TypeEnum.BOOL);

                    int var = CG3A.newVar(Variable.TipoVariable.VARIABLE, expressionNode.getType(), false, false);
                    CG3A.generate(InstructionType.NOT, new Operator3Address(expressionNode.getExp1().getReference()),
                            null,
                            new Operator3Address(var));

                    expressionNode.setReference(var);
                } else {
                    //Si no estabamos negando, será del tipo que sea la primera expresion de la expresion
                    expressionNode.setType(expressionNode.getExp1().getType());
                    expressionNode.setReference(expressionNode.getExp1().getReference());
                }
                //Si no tenemos solamente una expresion en la izquierda, sino que tambien una a la derecha
            } else if (expressionNode.getExp1() != null && expressionNode.getExp2() != null) {
                //Examinamos primero el lado izquierdo
                handleExpression(expressionNode.getExp1());
                //Y luego el derecho
                handleExpression(expressionNode.getExp2());
                //Si se trata de una operación aritmética...
                if (expressionNode.getBinOp().getArit() != null) {
                    //Si las dos expresiones tienen un tipo asignado (si no pasa, error)
                    if (expressionNode.getExp1().getType() != null && expressionNode.getExp2().getType() != null) {
                        // TODO: Check if we can match by TSB instead of hardcoding combinations
                        if (expressionNode.getExp1().getType() == TypeEnum.CHAR && expressionNode.getExp2().getType() == TypeEnum.INT) {
                            expressionNode.setType(TypeEnum.INT);
                            //Si las dos expresiones son del mismo tipo (no sumas int a double...)
                        } else if (expressionNode.getExp1().getType().equals(expressionNode.getExp2().getType())) {
                            //Procedemos a poner el tipo de la expresion (tanto vale Exp1 o Exp2) y generamos código
                            expressionNode.setType(expressionNode.getExp1().getType());
                        } else {
                            //One of types not declared error
                            parser.report_error("EXP error: El tipo de los operadores no coincide", expressionNode.getExp1());
                            expressionNode.setType(TypeEnum.NULL);
                        }

                        // Generate resulting code
                        int var = CG3A.newVar(Variable.TipoVariable.VARIABLE, expressionNode.getType(), false, false);
                        InstructionType operation = InstructionType.valueOf(expressionNode.getBinOp().getArit().getType().name());
                        CG3A.generate(operation, new Operator3Address(expressionNode.getExp1().getReference()),
                                new Operator3Address(expressionNode.getExp2().getReference()),
                                new Operator3Address(var));

                        expressionNode.setReference(var);
                    } else {
                        parser.report_error("EXP error: One or both operators type not detected", expressionNode.getBinOp());
                    }
                    //Si no es aritmetico, es relacional?
                } else if (expressionNode.getBinOp().getRel() != null) {
                    //Si es relacional, volvemos a comprobar que ambas expresiones tengan tipo
                    if (expressionNode.getExp1().getType() != null && expressionNode.getExp2().getType() != null) {
                        //Si pasa el check, miramos que sean del mismo tipo y la expresion será una comporbación, por tanto booleana
                        if (expressionNode.getExp1().getType().equals(expressionNode.getExp2().getType())) {
                            expressionNode.setType(TypeEnum.BOOL);

                            Integer var = CG3A.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.BOOL, false, false);
                            String label1 = CG3A.newLabel();
                            String label2 = CG3A.newLabel();
                            CG3A.generate(
                                    InstructionTypeUtils.getRelationalIf(expressionNode.getBinOp().getRel().getType()),
                                    new Operator3Address(expressionNode.getExp1().getReference()),
                                    new Operator3Address(expressionNode.getExp2().getReference()),
                                    new Operator3Address(label1));

                            CG3A.generate(
                                    InstructionType.CLONE,
                                    new Operator3Address(false, CastType.BOOL),
                                    null,
                                    new Operator3Address(var));

                            CG3A.generate(InstructionType.GOTO, null, null, new Operator3Address(label2));

                            CG3A.generate(InstructionType.SKIP, null, null, new Operator3Address(label1));

                            CG3A.generate(
                                    InstructionType.CLONE,
                                    new Operator3Address(true, CastType.BOOL),
                                    null,
                                    new Operator3Address(var));

                            CG3A.generate(InstructionType.SKIP, null, null, new Operator3Address(label2));
                            expressionNode.setReference(var);
                        } else {
                            parser.report_error("EXP error: El tipo de los operadores no coincide: " + expressionNode.getExp1().getType() + " y " + expressionNode.getExp2().getType() + " difieren.", expressionNode.getExp1());
                        }
                    } else {
                        parser.report_error("EXP error: Uno o más oepradores no detectados", expressionNode.getBinOp());
                    }
                    //Only logical operation left ("or" or "and")
                } else { //Same as before
                    if (expressionNode.getExp1().getType() != null && expressionNode.getExp2().getType() != null) {
                        //Same as before, but we check if boolean and put type accordingly
                        if (expressionNode.getExp1().getType() == TypeEnum.BOOL && expressionNode.getExp2().getType() == TypeEnum.BOOL) {
                            expressionNode.setType(TypeEnum.BOOL);
                        } else {
                            parser.report_error("EXP error: Type of operators not matching or it should be \"boolean\"", expressionNode.getExp1());
                        }

                        // Generate resulting code
                        int var = CG3A.newVar(Variable.TipoVariable.VARIABLE, expressionNode.getType(), false, false);
                        InstructionType operation = InstructionType.valueOf(expressionNode.getBinOp().getArit().getType().name());
                        CG3A.generate(operation, new Operator3Address(expressionNode.getExp1().getReference()),
                                new Operator3Address(expressionNode.getExp2().getReference()),
                                new Operator3Address(var));

                        expressionNode.setReference(var);
                    } else {
                        parser.report_error("EXP error: One or both operators type not detected", expressionNode.getExp1());
                    }
                }
                //If no more expressions found and we find a simple value...
            } else if (expressionNode.getSimplVal() != null) {
                handleSimpleValue(expressionNode.getSimplVal());
                //Expression type is the same, we analyzing this: (2 of "(2+(5*(6 mod 3)))" -> Exp1 = 2, Exp2 = (5*(6 mod 3)), Op = BinOp = "+")
                expressionNode.setType(expressionNode.getSimplVal().getType());
                //Node reference will point to this simple value
                expressionNode.setReference(expressionNode.getSimplVal().getReference());

            }
        } else {
            parser.report_error("Trying to handle assignation with null expression", expressionNode);
        }
    }
    
    public void handleDeclTupel(DeclTupelNode declTupel){
        if(declTupel.getId() != null){
            int nVar = CG3A.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.TUPEL, false, true);
            boolean init = declTupel.getTupeldecl() != null ? true : false;
            ts.poner(declTupel.getId().getIdentifierLiteral(), new TupelDescripcion(nVar, TypeEnum.TUPEL, true, init));
            if(declTupel.getParams() != null && !declTupel.getParams().isEmpty() && declTupel.getTupeldecl() != null){
                handleParamList(declTupel.getParams());
                handleTupelDecl(declTupel.getTupeldecl());
            }
        }else{
            parser.report_error("La tupla no tiene un identificador definido!", declTupel);
        }
    }
    
    public void handleTupelDecl(TupelDeclNode tupelDecl){
        if(tupelDecl.getInit() != null){
            handleInitTupel(tupelDecl.getInit());
        }else{
            parser.report_error("La tupla no está inicializada o hay otro error de inicialización!", tupelDecl);
        }
    }
    
    public void handleInitTupel(InitTupelNode initTupel){
        //TODO: Completar método
    }
    /**
     * @Manu PROGRAM*; DECL_LIST*; DECL*; ACTUAL_DECL*; DECL_ELEM*; TYPE_ID? (if
     * not x, error?); ELEM_LIST*; ELEM_ID_ASSIG*; DECL_ARRAY*; DIM_ARRAY*;
     * ARRAY_DECL*; INIT_ARRAY*; DECL_TUPEL; TUPEL_DECL; INIT_TUPEL; EXP*;
     * @Coti METHOD_LIST; METHOD; PROC; FUNC; PARAM_LIST; ACTUAL_PARAM_LIST;
     * PARAM; SPECIAL_PARAM; SENTENCE_LIST; SENTENCE; FOR_INST; NEXT_IF; INST;
     * INST_EXP; METHOD_CALL;
     * @Constantino PARAM_IN; ASSIG; SIMPLE_VALUE; GEST_IDX; GESTOR; LITERAL;
     * BINARY_OP; REL_OP; LOGIC_OP; ARIT_OP; NEG_OP; SPECIAL_OP; MAIN; MODIFIER;
     * ID;
     */
//DEADLINE: 14-01-2023 -> Tenerlos hechos (no hace falta que bien), quedar y arreglarlos (si hace falta).

    // COTI
    public void handleMethodList(MethodListNode node) {
        if (node.getMethod() != null) {
            handleMethod(node.getMethod());
        }
        if (node.getMethodList() != null && !(node.getMethodList().isEmpty())) {
            handleMethodList(node.getMethodList());
        }
    }

    public void handleMethod(MethodNode node) {
        if (node.getProc() != null) {
            handleProc(node.getProc());
        }
        if (node.getFunc() != null) {
            handleFunc(node.getFunc());
        }
    }

    public void handleProc(ProcNode node) {
        String idProc = node.getIdentifier().getIdentifierLiteral();
        gc.addFunctionId(idProc); //ponemos el procedimiento en la cima de la pila de procedimientos activos
        String label = gc.newLabel();
        int numProc = gc.newProcedure(idProc, ts.getActual(), label, 0);
        ProcDescripcion d = new ProcDescripcion(numProc);
        try {
            ts.poner(idProc, d);
        } catch (IllegalArgumentException e) {
            parser.report_error("Procedimiento " + idProc + " ya está definido", node);
            tp.decrement();
        }

        ts.entrarBloque();

        if (node.getParamList() != null && !(node.getParamList().isEmpty())) {
            handleParamList(node.getParamList(), idProc);
        }

        gc.generate(InstructionType.SKIP, null, null, new Operator3Address(label)); //generamos SKIP para saber donde saltar al hacer call de este procedimiento
        gc.generate(InstructionType.PMB, null, null, new Operator3Address(idProc));

        if (node.getSentenceList() != null) {
            handleSentenceList(node.getSentenceList());
        }

        gc.removeFunctionId(); //quitamos el procedimiento de la pila de procedimientos activos

        gc.generate(InstructionType.RETURN, new Operator3Address(idProc), null, null);

        ts.salirBloque();
    }

    public void handleFunc(FuncNode node) {
        TypeEnum tipo = node.getTypeId().getType();
        String idProc = node.getId().getIdentifierLiteral();
        gc.addFunctionId(idProc); //ponemos el procedimiento en la cima de la pila de procedimientos activos
        String label = gc.newLabel();
        int numProc = gc.newProcedure(idProc, ts.getActual(), label, 0);
        ProcDescripcion d = new ProcDescripcion(numProc);
        try {
            ts.poner(idProc, d);
        } catch (IllegalArgumentException e) {
            parser.report_error("Función " + idProc + " ya está definido", node);
            tp.decrement();
        }

        ts.entrarBloque();

        if (node.getParamList() != null && !(node.getParamList().isEmpty())) {
            handleParamList(node.getParamList().getActualParamList());
        }

        gc.generate(InstructionType.SKIP, null, null, new Operator3Address(label)); //generamos SKIP para saber donde saltar al hacer call de este procedimiento
        gc.generate(InstructionType.PMB, null, null, new Operator3Address(idProc));

        if (node.getSentenceList() != null) {
            handleSentenceList(node.getSentenceList());
        }

        gc.removeFunctionId(); //quitamos el procedimiento de la pila de procedimientos activos

        handleExpresion(node.getExp());
        if (tipo != node.getExp().getType()) {
            parser.report_error("Se intenta devolver un dato cuyo tipo no es el mismo que el definido por la función", node);
        }
        gc.generate(InstructionType.RETURN, new Operator3Address(idProc), null, new Operator3Address(node.getExp().getReference()));

        ts.salirBloque();
    }

    public void handleParamList(ActualParamListNode node) {
        if (node.getParam() != null) {
            handleParam(node.getParam());
        }
        if (node.getActualParamList() != null) {
            handleParamList(node.getActualParamList());
        }
    }

    public void handleParam(ParamNode node) {
        String actualProc = gc.getCurrentFunction();
        String id = node.getId().getIdentifierLiteral();
        TypeEnum tipo = TypeEnum.NULL;
        boolean isArray = false;
        boolean isTupel = false;
        if (node.getSpecialParam() == null) {
            tipo = node.getTypeId().getType();
        } else {
            if (node.getSpecialParam().getTypeId() == null) {
                isTupel = true;
            } else {
                isArray = true;
                tipo = node.getSpecialParam().getTypeId().getType();
            }
        }
        // metemos dentro de la tabla de expansión el paràmetro
        ArgDescripcion d = new ArgDescripcion(tipo, id);
        ts.ponerParam(actualProc, id, d);
        tp.get(actualProc).setNumberParameters(tp.get(actualProc).getNumberParameters() + 1);

        //metemos en la tabla de descripción dicho parametro para ser usado cuando se invoque el programa asociado
        int numParam = gc.newVar(Variable.TipoVariable.PARAM, tipo, isArray, isTupel);
        VarDescripcion dvar = new VarDescripcion(numParam, tipo);
        try {
            ts.poner(id, dvar);
        } catch (IllegalArgumentException e) {
            parser.report_error("Variable " + id + " ya está definida", node);
            tp.decrement();
        }
    }

    public void handleSentenceList(SentenceListNode node) {
        if (node.getSentence() != null) {
            handleSentence(node.getSentence());
        }
        if (node.getSentenceList() != null && !(node.getSentenceList().isEmpty())) {
            handleSentenceList(node.getSentenceList());
        }
    }

    public void handleSentence(SentenceNode node) {
        switch (node.getSentenceType()) {
            case IF:
                handleExpresion(node.getExpression());
                if (node.getExpression().getType() != TypeEnum.BOOL) {
                    parser.report_error("La expressión a evaluar en el condicional no és booleana", node.getExpression());
                }
                String et = gc.newLabel();
                gc.generate(InstructionType.IFEQ, new Operator3Address(node.getExpression().getReference()), new Operator3Address(0, CastType.INT), new Operator3Address(et));
                if (node.getSentenceList() != null) {
                    handleSentenceList(node.getSentenceList());
                }
                String eti = gc.newLabel();
                gc.generate(InstructionType.GOTO, null, null, new Operator3Address(eti));
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(et));
                if (node.getNextIf() != null) {
                    handleNextIf(node.getNextIf());
                }
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(eti));

                break;
            case WHILE:
                String againWhile = gc.newLabel();
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(againWhile));
                handleExpresion(node.getExpression());
                if (node.getExpression().getType() != TypeEnum.BOOL) {
                    parser.report_error("La expressión a evaluar en el condicional no és booleana", node.getExpression());
                }
                String finalWhile = gc.newLabel();
                gc.generate(InstructionType.IFEQ, new Operator3Address(node.getExpression().getReference()), new Operator3Address(0, CastType.INT), new Operator3Address(finalWhile));
                if (node.getSentenceList() != null) {
                    handleSentenceList(node.getSentenceList());
                }
                gc.generate(InstructionType.GOTO, null, null, new Operator3Address(againWhile));
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(finalWhile));
                break;
            case DECL:
                handleDecl(node.getDecl());
                break;
            case INST:
                handleInst(node.getInst());
                break;
            case FOR:
                if (node.getDecl() != null) {
                    handleDecl(node.getDecl());
                }
                String againFor = gc.newLabel();
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(againFor));
                handleExpresion(node.getExpression());
                if (node.getExpression().getType() != TypeEnum.BOOL) {
                    parser.report_error("La expressión a evaluar en el condicional no és booleana", node.getExpression());
                }
                String finalFor = gc.newLabel();
                gc.generate(InstructionType.IFEQ, new Operator3Address(node.getExpression().getReference()), new Operator3Address(0, CastType.INT), new Operator3Address(finalFor));
                if (node.getSentenceList() != null) {
                    handleSentenceList(node.getSentenceList());
                }
                if (node.getForInst() != null) {
                    String id = node.getForInst().getIdentifier().getIdentifierLiteral();
                    IdDescripcion d = ts.consultaId(id);

                    if (node.getExpression() == null) {
                        handleSpecialOp(node.getForInst().getSpecialOp(), node.getForInst().getIdentifier());
                    } else { // ID = EXP
                        handleExpresionAssig(node.getForInst().getExpression(), node.getForInst().getIdentifier());
                    }
                }
                gc.generate(InstructionType.GOTO, null, null, new Operator3Address(againFor));
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(finalFor));
                break;
            case REPEAT:
                String againRepeat = gc.newLabel();
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(againRepeat));
                if (node.getSentenceList() != null) {
                    handleSentenceList(node.getSentenceList());
                }
                handleExpresion(node.getExpression());
                if (node.getExpression().getType() != TypeEnum.BOOL) {
                    parser.report_error("La expressión a evaluar en el condicional no és booleana", node.getExpression());
                }
                String finalRepeat = gc.newLabel();
                gc.generate(InstructionType.IFEQ, new Operator3Address(node.getExpression().getReference()), new Operator3Address(0, CastType.INT), new Operator3Address(finalRepeat));
                if (node.getSentenceList() != null) {
                    handleSentenceList(node.getSentenceList());
                }
                gc.generate(InstructionType.GOTO, null, null, new Operator3Address(againRepeat));
                gc.generate(InstructionType.SKIP, null, null, new Operator3Address(finalRepeat));
                break;
            case NONE:
                //no hacer nada dado que se trata de un error que se gestiona más tarde
                break;
        }
    }

    public void handleSpecialOp(SpecialOpNode node, IdentifierNode id) {
        IdDescripcion d = ts.consultaId(id.getIdentifierLiteral());
        if (d.getTipoDescripcion() == TipoDescripcion.dvar) {
            VarDescripcion dvar = (VarDescripcion) d;
            if (dvar.getType() == TypeEnum.INT) {
                int result = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                if (node.getType() == SpecialOpType.INCREMENT) {
                    gc.generate(InstructionType.ADD, new Operator3Address(dvar.getVariableNumber()), new Operator3Address(1, CastType.INT), new Operator3Address(result));
                } else {
                    //decrement
                    gc.generate(InstructionType.SUB, new Operator3Address(dvar.getVariableNumber()), new Operator3Address(1, CastType.INT), new Operator3Address(result));
                }
                gc.generate(InstructionType.CLONE, new Operator3Address(result), null, new Operator3Address(dvar.getVariableNumber()));
                node.setReference(result);
            } else {
                parser.report_error("La variable a incrementar/decrementar no es de tipo entero", id);
            }
        } else {
            parser.report_error("El identificador no es una variable", id);
        }
    }

    public void handleNextIf(NextIfNode node) {
        if (node.getExpression() == null) { // else
            if (node.getSentenceList() != null) {
                handleSentenceList(node.getSentenceList());
            }
        } else { // elif
            handleExpresion(node.getExpression());
            if (node.getExpression().getType() != TypeEnum.BOOL) {
                parser.report_error("La expressión a evaluar en el condicional no és booleana", node.getExpression());
            }
            String et = gc.newLabel();
            gc.generate(InstructionType.IFEQ, new Operator3Address(node.getExpression().getReference()), new Operator3Address(0, CastType.INT), new Operator3Address(et));
            if (node.getSentenceList() != null) {
                handleSentenceList(node.getSentenceList());
            }
            String eti = gc.newLabel();
            gc.generate(InstructionType.GOTO, null, null, new Operator3Address(eti));
            gc.generate(InstructionType.SKIP, null, null, new Operator3Address(et));
            if (node.getNextIf() != null) {
                handleNextIf(node.getNextIf());
            }
            gc.generate(InstructionType.SKIP, null, null, new Operator3Address(eti));
        }
    }

    public void handleInst(InstNode node) {
        switch (node.getInstType()) {
            case EXP:
                if (node.getInstExp() != null) {
                    handleInstExp(node.getInstExp());
                }
                break;
            case ASSIG:
                if (node.getAssig() != null) {
                    handleAssig(node.getAssig());
                }
                break;
            case PRINT:
                handleExpresion(node.getPrintExpression());
                if (node.getPrintExpression().getType() == TypeEnum.NULL) {
                    parser.report_error("Expresion no válida para hacer print", node.getPrintExpression());
                }
                gc.generate(InstructionType.PRINT, new Operator3Address(node.getPrintExpression().getReference()), null, null);
                break;
            case PRINTLN:
                handleExpresion(node.getPrintExpression());
                if (node.getPrintExpression().getType() == TypeEnum.NULL) {
                    parser.report_error("Expresion no válida para hacer print", node.getPrintExpression());
                }
                gc.generate(InstructionType.PRINTLN, new Operator3Address(node.getPrintExpression().getReference()), null, null);
                break;
        }
    }

    private void handleInstExp(InstExpNode node) {
        if (node.getSpecialOp() != null) {
            handleSpecialOp(node.getSpecialOp(), node.getIdentifier());
            node.setReference(node.getSpecialOp().getReference());
        } else if (node.getMethodCall() != null) {
            handleMethodCall(node.getMethodCall());
            if (node.getMethodCall().getReference() != -1) {
                //FUNC
                node.setReference(node.getMethodCall().getReference());
            }
        } else {
            int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.CHAR, false, false);
            gc.generate(InstructionType.READ, null, null, new Operator3Address(var));
            node.setReference(var);
        }
    }

    public void handleMethodCall(MethodCallNode node) {
        IdDescripcion d = ts.consultaId(node.getIdentifier().getIdentifierLiteral());
        if (d.getTipoDescripcion() == TipoDescripcion.dproc || d.getTipoDescripcion() == TipoDescripcion.dfunc) {
            ArrayList<ArgDescripcion> param = ts.consultarParams(node.getIdentifier().getIdentifierLiteral());
            if (node.getParamIn() != null && !(node.getParamIn().isEmpty())) {
                ArrayList<ExpressionNode> paramIn = new ArrayList<>();
                handleParamIn(node.getParamIn(), paramIn);
                if (param.size() != paramIn.size()) {
                    parser.report_error("No se han escrito el número de parámetros correspondientes", node.getParamIn());
                }
                for (int i = 0; i < param.size(); i++) {
                    if (param.get(i).getType() != paramIn.get(i).getType()) {
                        parser.report_error("Uno de los parámetros introducido no tiene el tipo correspondiente", node.getParamIn());
                    }
                    gc.generate(InstructionType.SIMPLEPARAM, null, null, new Operator3Address(node.getParamIn().getExpression().getReference()));
                }
            } else {
                //la llamada no tiene parámetros
                if (param != null) {
                    parser.report_error("La llamada requiere de parámetros", node.getParamIn());
                }
            }
            int res = -1;
            if (d.getTipoDescripcion() == TipoDescripcion.dfunc) {
                FuncDescripcion dfunc = (FuncDescripcion) d;
                res = gc.newVar(Variable.TipoVariable.VARIABLE, dfunc.getType(), false, false);
                gc.generate(InstructionType.CALL, new Operator3Address(node.getIdentifier().getIdentifierLiteral()), null, new Operator3Address(res));
            } else {
                ProcDescripcion dproc = (ProcDescripcion) d;
                gc.generate(InstructionType.CALL, new Operator3Address(node.getIdentifier().getIdentifierLiteral()), null, null);
            }
            node.setReference(res);
        } else {
            parser.report_error("Este identificador no corresponde a ningún subprograma", node.getIdentifier());
        }
    }

    public void handleParamIn(ParamInNode node, ArrayList<ExpressionNode> res) {
        res.add(node.getExpression());
        if (node.getParamIn() != null) {
            handleParamIn(node.getParamIn(), res);
        }
    }

// CONSTANTIN
    public void handleAssig(AssigNode assigNode) {
        IdDescripcion d = ts.consultaId(assigNode.getGestIdx().getId().getIdentifierLiteral());
        handleGestIdx(assigNode.getGestIdx());
        if (assigNode.getInitArray() != null || assigNode.getInitTupel() != null) {
            if (assigNode.getInitArray() != null) {
                if (d.getTipoDescripcion() != TipoDescripcion.darray) {
                    parser.report_error("Declaration error in assign handler: id is not an array", assigNode.getInitArray());
                } else {
                    ArrayDescripcion darray = (ArrayDescripcion) d;
                    if (!darray.isVar()) {
                        if (darray.isInit()) {
                            parser.report_error("Assig error: " + assigNode.getGestIdx().getId().getIdentifierLiteral() + " is an already declared constant", assigNode.getInitArray());
                        } else {
                            darray.setInit(true);
                            handleInitArray(assigNode.getInitArray(), darray.getType(), darray);
                            gc.generate(InstructionType.CLONE, new Operator3Address(assigNode.getInitArray().getReference()), null, new Operator3Address(assigNode.getGestIdx().getReference()));
                        }
                    }
                }
            } else {
                if (d.getTipoDescripcion() != TipoDescripcion.dtupel) {
                    parser.report_error("Declaration error in assign handler: id is not a tuple", assigNode.getInitTupel());
                } else {
                    TupelDescripcion dtupel = (TupelDescripcion) d;
                    if (!dtupel.isVar()) {
                        if (dtupel.isInit()) {
                            parser.report_error("Assig error: " + assigNode.getGestIdx().getId().getIdentifierLiteral() + " is an already declared constant", assigNode.getInitArray());
                        } else {
                            dtupel.setInit(true);
                            handleInitTupel(assigNode.getInitArray(), dtupel.getType(), dtupel);
                            gc.generate(InstructionType.CLONE, new Operator3Address(assigNode.getInitTupel().getReference()), null, new Operator3Address(assigNode.getGestIdx().getReference()));
                        }
                    }
                }
            }
        } else {
            handleExpresion(assigNode.getExpression1());
            if(d.getTipoDescripcion() == TipoDescripcion.dconst){
                parser.report_error("Se intenta asignar un valor a una constante",assigNode.getGestIdx());
            }
            switch(d.getTipoDescripcion()){
                case dvar:
                    VarDescripcion dvar = (VarDescripcion) d;
                    if(dvar.getType() != assigNode.getExpression1().getType()){
                        parser.report_error("El tipo subyacente no coincide con el de la expresión",assigNode.getGestIdx());
                    }
                    gc.generate(InstructionType.CLONE, new Operator3Address(assigNode.getExpression1().getReference()), null, new Operator3Address(assigNode.getGestIdx().getReference()));
                break;
                case darray: // indexacion  se tiene que terminar
                    ArrayDescripcion darray = (ArrayDescripcion) d;
                break;
                case dtupel: // indexacion se tiene que terminar
                    TupelDescripcion dtupel = (TupelDescripcion) d;
                break;
                default:
                    parser.report_error("El tipo del identificador no puede ser usado para una asignación",assigNode.getGestIdx());
            } 
        }
    }

    public void handleElemList(ElemListNode elemList, TipoDescripcion td, TypeEnum tipo) {

        ElemListNode elemListNode = elemList.getElemList();

        if (elemListNode != null) {
            handleElemList(elemListNode, td, tipo);
        }

        handleElemIdAssig(elemList.getElemIdAssig(), td, tipo);
    }

    public void handleElemIdAssig(ElemIdAssigNode elemIdAssig, TipoDescripcion td, TypeEnum tipo) {

        String id = (String) elemIdAssig.getIdentifier().getIdentifierLiteral();
        ExpressionNode exp = elemIdAssig.getExp();

        int resultVarExp = -9; //ns por la vd

        if (exp != null) {
            handleExpression(exp);
            resultVarExp = exp.getReference();
            if (tipo != exp.getType()) {
                parser.report_error("Expression does not match the identifier type", exp);
            }
        }

        int varNum;

        switch (td) {
            case dvar:

                if (tipo != TypeEnum.STRING) {
                    varNum = gc.newVar(Variable.TipoVariable.VARIABLE, tipo, false, false);
                    VarDescripcion var = new VarDescripcion(varNum, tipo);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Variable " + id + " is already defined", elemIdAssig);
                        tv.decrement();
                    }
                } else {
                    varNum = gc.newVar(Variable.TipoVariable.VARIABLE, tipo, false, false);
                    StringDescripcion var = new StringDescripcion(varNum, true, exp != null);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Variable " + id + " is already defined", elemIdAssig);
                        tv.decrement();
                    }
                }

                //Si asignacion dada
                if (resultVarExp != -9) {
                    gc.generate(InstructionType.CLONE, new Operator3Address(resultVarExp), null, new Operator3Address(varNum));
                }
                break;
            case dconst:
                if (tipo != TypeEnum.STRING) {
                    varNum = gc.newVar(Variable.TipoVariable.VARIABLE, tipo, false, false);
                    ConstDescripcion constante = new ConstDescripcion(varNum, tipo, exp != null);
                    try {
                        ts.poner(id, constante);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Constant " + id + " is already defined", elemIdAssig);
                        tv.decrement();
                    }
                } else {
                    varNum = gc.newVar(Variable.TipoVariable.VARIABLE, tipo, false, false);
                    StringDescripcion var = new StringDescripcion(varNum, false, exp != null);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Constant " + id + " is already defined", elemIdAssig);
                        tv.decrement();
                    }
                }

                // If expression given, make the assignation
                if (resultVarExp != -9) {
                    gc.generate(InstructionType.CLONE, new Operator3Address(resultVarExp), null, new Operator3Address(varNum));
                }
                break;
            default:
                break;

        }

    }

    private int handleLiteral(String value, TypeEnum tipo) {
        //temporalPointer for latter assignment of reference if needed
        int varRef = gc.newVar(Variable.TipoVariable.VARIABLE, tipo, false, false);
        Operator3Address opValue;
        switch (tipo) {
            case INT:
                //Code generation for int literal
                opValue = new Operator3Address(Integer.parseInt(value), CastType.INT);
                break;
            case CHAR:
                //Code generation for char literal
                opValue = new Operator3Address(value.charAt(0), CastType.CHAR);
                break;
            case BOOL:
                //Code generation for bool literal
                opValue = new Operator3Address(Boolean.parseBoolean(value), CastType.BOOL);
                break;
            case STRING:
                //Code generation for string literal
                opValue = new Operator3Address(value, CastType.STRING);
                break;
            default:
                parser.report_error("Trying to generate code for literal type: " + tipo.getTypeString(), null);
                opValue = new Operator3Address(value);
                break;
        }
        //Code generation for an assignment of a variable
        gc.generate(InstructionType.CLONE, opValue, null, new Operator3Address(varRef));

        return varRef;
    }

    private void handleMain(MainNode mainNode) {
        // TODO: A better solution would be to attach each variable in descriptionTable
        // to a procedure, so we can have the same var, in the same scope, for different procedures
        ts.entrarBloque();
        ts.entrarBloque();
        if (mainNode.getSentenceList() != null && !mainNode.getSentenceList().isEmpty()) {
            handleSentenceList(mainNode.getSentenceList());
        }
        gc.generate(InstructionType.RETURN, new Operator3Address("main"), null, null);
        ts.salirBloque();
        ts.salirBloque();
    }
    /**
     * @Manu PROGRAM; DECL_LIST; DECL; ACTUAL_DECL; DECL_ELEM; DECL_ARRAY; DIM_ARRAY; ARRAY_DECL; INIT_ARRAY; DECL_TUPEL; TUPEL_DECL; INIT_TUPEL; EXP;
     * SIMPLE_VALUE; GEST_IDX; GESTOR; COTI : handleExpression debe poner el resultado como referencia en el nodo porfa jej , màs que nada todo nodo y derivados
     * que se usa en assig deberá hacerse esto
     * @Constantino ; ASSIG; TYPE_ID; // COTI : no necesita método ELEM_LIST; ELEM_ID_ASSIG; LITERAL; BINARY_OP; REL_OP; LOGIC_OP; ARIT_OP; NEG_OP; SPECIAL_OP
     * //COTI : lo he hecho sin querer xd; MAIN; MODIFIER; ID;
     */
    //DEADLINE: 14-01-2023 -> Tenerlos hechos (no hace falta que bien), quedar y arreglarlos (si hace falta).
}
