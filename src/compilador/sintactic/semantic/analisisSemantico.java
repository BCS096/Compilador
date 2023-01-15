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
import tablas.ArrayDescripcion;
import tablas.ConstDescripcion;
import tablas.Data;
import tablas.IdDescripcion;
import tablas.ProcDescripcion;
import tablas.StringDescripcion;
import tablas.TablaProcedimientos;
import tablas.TablaSimbolos;
import tablas.TablaVariables;
import tablas.TupelDescripcion;
import tablas.TypeDescripcion;
import tablas.VarDescripcion;
import tablas.Variable;
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
    private final CodeGeneration3Address CG3A;

    public analisisSemantico(ProgramNode program, Parser parser) {
        this.programNode = program;
        this.parser = parser;
        this.ts = new TablaSimbolos();
        this.tv = new TablaVariables();
        this.tp = new TablaProcedimientos();
        this.CG3A = new CodeGeneration3Address(tv, tp);

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
}
