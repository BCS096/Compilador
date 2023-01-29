/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.semantic;

import compilador.ensamblado.AssemblyGenerator;
import compilador.main.MVP;
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
    private final MVP mvp;
    private final TablaSimbolos ts;
    private final TablaVariables tv;
    private final TablaProcedimientos tp;
    private final CodeGeneration3Address gc;
    private static String tempId = null;

    public analisisSemantico(ProgramNode program, Parser parser) {
        this.programNode = program;
        this.parser = parser;
        this.ts = new TablaSimbolos();
        this.tv = new TablaVariables();
        this.tp = new TablaProcedimientos();
        this.gc = new CodeGeneration3Address(tv, tp);
        this.mvp = parser.getMVP();

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
        //Switch al output de semantico
        mvp.semantic(true);
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

            gc.generate(InstructionType.CALL, new Operator3Address("main"), null, null);
            handleMain(main);
        } else {
            parser.report_error("ERROR - No se ha declarado 'main'", programNode);
        }
        mvp.semanticCode(new StringBuilder("instructionType | operator1 | operator2 | result\n"));
        gc.getInstruccions().forEach(ins -> {
            mvp.semanticCode(new StringBuilder(ins.toString() + '\n'));
        });
        AssemblyGenerator ensamblado = new AssemblyGenerator(mvp.getActualFile(), tv, tp, gc.getInstruccions());
        ensamblado.mainMake();
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
        } else {
            mod = new ModifierNode(IdDescripcion.TipoDescripcion.dconst, false, 0, 0);
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
//Sin pasar type desde aquí no se puede saber luego

    public void handleDeclElem(DeclElemNode declElem, IdDescripcion.TipoDescripcion modifier) {
        TypeEnum type = declElem.getTypeId().getType();
        //Una lista de elementos
        handleElemList(declElem.getElemList(), type, modifier);
    }

    public void handleElemList(ElemListNode elemList, TypeEnum type, IdDescripcion.TipoDescripcion modifier) {
        ElemListNode listaElementos = elemList.getElemList();
        if (listaElementos != null) {
            handleElemList(listaElementos, type, modifier);
        }
        //Es un solo elemento, o final de lista
        handleElemIdAssig(elemList.getElemIdAssig(), type, modifier);
    }

    public void handleElemIdAssig(ElemIdAssigNode elemIdAssig, TypeEnum type, IdDescripcion.TipoDescripcion modifier) {
        String id = (String) elemIdAssig.getIdentifier().getIdentifierLiteral();
        ExpressionNode expression = elemIdAssig.getExp();
        //IdDescripcion.TipoDescripcion modifier = ts.consultaId(elemIdAssig.getIdentifier().getIdentifierLiteral()).getTipoDescripcion();
        //check si tiene valor literal (será constante), si no es dvar.
        int expResultVarNumber = -9;
        if (expression != null) {
            handleExpresion(expression);
            expResultVarNumber = expression.getReference();
            if (type != expression.getType()) {
                parser.report_error("Identificador no coincide con la exp", expression);
            }
        }

        int nVar;
        switch (modifier) {
            case dvar:
                if (type != TypeEnum.STRING) {
                    nVar = gc.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
                    VarDescripcion var = new VarDescripcion(nVar, type);
                    try {
                        ts.poner(id, var);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Variable " + id + " ya se ha definido", elemIdAssig);
                        tv.decrement();
                    }
                } else {
                    nVar = gc.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
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
                    gc.generate(InstructionType.CLONE, new Operator3Address(expResultVarNumber), null, new Operator3Address(nVar));
                }
                break;
            case dconst:
                if (type != TypeEnum.STRING) {
                    nVar = gc.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
                    ConstDescripcion constant = new ConstDescripcion(nVar, type, expression != null);
                    try {
                        ts.poner(id, constant);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Constante " + id + " ya está definida", elemIdAssig);
                        tv.decrement();
                    }
                } else {
                    nVar = gc.newVar(Variable.TipoVariable.VARIABLE, type, false, false);
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
                    gc.generate(InstructionType.CLONE, new Operator3Address(expResultVarNumber), null, new Operator3Address(nVar));
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
                if (tipo == TypeEnum.ARRAY || tipo == TypeEnum.TUPEL) {
                    switch (tipo) {
                        case ARRAY:
                            parser.syntax_error(new java_cup.runtime.Symbol(25));
                            break;
                        case TUPEL:
                            parser.syntax_error(new java_cup.runtime.Symbol(26));
                            break;
                    }
                } else {
                    int nVar = gc.newVar(Variable.TipoVariable.VARIABLE, tipo, true, false);
                    arr = new ArrayDescripcion(nVar, tipo, initialized);
                    try {
                        ts.poner(id, arr);
                    } catch (IllegalArgumentException e) {
                        parser.report_error("Variable " + id + " ya está definida", declArray);
                        tv.decrement();
                    }
                    break;
                }
            case dconst:
                parser.syntax_error(new java_cup.runtime.Symbol(39));
            default:
                throw new RuntimeException("No es una constante ni una variable!");
        }

        if (initialized) {
            //Caso de que ya esté inicializado (tiene su declaración y eso significa que si)
            handleArrayDecl(declArray.getArrayDecl(), tipo, id);
        }
    }

    public void handleDimArray(DimArrayNode dimArray, String id) {
        if (dimArray.getDim() != null) {
            handleExpresion(dimArray.getDim());
            if (dimArray.getDim().getType() != TypeEnum.INT) {
                IndexDescripcion idxd = new IndexDescripcion(dimArray.getDim().getReference());
                ts.ponerIndice(id, idxd);
                if (dimArray.getNextDim() != null && !dimArray.getNextDim().isEmpty()) {
                    handleDimArray(dimArray.getNextDim(), id);
                }
            } else {
                parser.report_error("El tipo de la expresión que representa la dimensión no es un entero", dimArray.getDim());
            }

        } else {
            parser.report_error("No valid dimension found!", dimArray);
        }
    }

    public void handleInitArray(InitArrayNode initArray, TypeEnum type, String id) {
        //Comprobación de tipo ya no es posible, no está en la producción. Pasarla como parámetro de alguna manera?????
        if (type != initArray.getTypeId().getType()) {
            parser.report_error("No coincide el tipo en la istancia de la array", initArray.getTypeId());
        } else {
            if (initArray.getDimArray() != null) {
                handleDimArray(initArray.getDimArray(), id);
            } else {
                parser.report_error("No se ha encontrado la dimensión del array!", initArray);
            }
        }
    }

    public void handleArrayDecl(ArrayDeclNode node, TypeEnum type, String id) {
        if (node.getInitArrayNode() != null) {
            handleInitArray(node.getInitArrayNode(), type, id);
        }
    }

    public void handleExpresion(ExpressionNode expressionNode) {
        if (expressionNode != null) {
            //Si tenemos alguna expresion en el lado izquierdo, pero no en el derecho
            if (expressionNode.getExp1() != null && expressionNode.getExp2() == null) {
                //Analizamos la expresion (analizaremos hacia la izquierda y abajo todo lo posible primero)
                handleExpresion(expressionNode.getExp1());
                //Si tenemos un operador de negacion ("not")
                if (expressionNode.getNegOp() != null) {
                    //Si queremos negar, tendremos que negar un booleano, no puede ser otra cosa
                    if (expressionNode.getExp1().getType() != TypeEnum.BOOL) {
                        parser.report_error("Trying to apply not to a non boolean value, got type: " + expressionNode.getExp1().getType(), expressionNode.getNegOp());
                    }
                    //Entonces nuestra expresion será booleana
                    expressionNode.setType(TypeEnum.BOOL);

                    int var = gc.newVar(Variable.TipoVariable.VARIABLE, expressionNode.getType(), false, false);
                    gc.generate(InstructionType.NOT, new Operator3Address(expressionNode.getExp1().getReference()),
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
                handleExpresion(expressionNode.getExp1());
                //Y luego el derecho
                handleExpresion(expressionNode.getExp2());
                //Si se trata de una operación aritmética...
                if (expressionNode.getBinOp().getArit() != null) {
                    /*Aqui se puede dar un unico caso (si todo esta bien implementado) que es cuando hay un read. 
                    Este caso indica que es NULL, pero tiene que adoptar el mismo que el EXP2 o viceversa
                     */
                    if (expressionNode.getExp1().getType() == null) {
                        expressionNode.getExp1().setType(TypeEnum.CHAR);
                    } else if (expressionNode.getExp2().getType() == null) {
                        expressionNode.getExp2().setType(TypeEnum.CHAR);
                    }
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
                        int var = gc.newVar(Variable.TipoVariable.VARIABLE, expressionNode.getType(), false, false);
                        InstructionType operation = InstructionType.valueOf(expressionNode.getBinOp().getArit().getType().name());
                        gc.generate(operation, new Operator3Address(expressionNode.getExp1().getReference()),
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

                            Integer var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.BOOL, false, false);
                            String label1 = gc.newLabel();
                            String label2 = gc.newLabel();
                            gc.generate(
                                    InstructionTypeUtils.getRelationalIf(expressionNode.getBinOp().getRel().getType()),
                                    new Operator3Address(expressionNode.getExp1().getReference()),
                                    new Operator3Address(expressionNode.getExp2().getReference()),
                                    new Operator3Address(label1));

                            gc.generate(
                                    InstructionType.CLONE,
                                    new Operator3Address(false, CastType.BOOL),
                                    null,
                                    new Operator3Address(var));

                            gc.generate(InstructionType.GOTO, null, null, new Operator3Address(label2));

                            gc.generate(InstructionType.SKIP, null, null, new Operator3Address(label1));

                            gc.generate(
                                    InstructionType.CLONE,
                                    new Operator3Address(true, CastType.BOOL),
                                    null,
                                    new Operator3Address(var));

                            gc.generate(InstructionType.SKIP, null, null, new Operator3Address(label2));
                            expressionNode.setReference(var);
                        } else {
                            parser.report_error("EXP error: El tipo de los operadores no coincide: " + expressionNode.getExp1().getType() + " y " + expressionNode.getExp2().getType() + " difieren.", expressionNode.getExp1());
                        }
                    } else {
                        parser.report_error("EXP error: Uno o más operadores no detectados", expressionNode.getBinOp());
                    }
                    //Only logical operation left ("or" or "and")
                } else { //Same as before
                    if (expressionNode.getExp1().getType() != null && expressionNode.getExp2().getType() != null) {
                        //Same as before, but we check if boolean and put type accordingly
                        if (expressionNode.getExp1().getType().equals(expressionNode.getExp2().getType())) {
                            expressionNode.setType(TypeEnum.BOOL);
                        } else {
                            parser.report_error("EXP error: Type of operators not matching or it should be \"boolean\"", expressionNode.getExp1());
                        }

                        // Generate resulting code
                        int var = gc.newVar(Variable.TipoVariable.VARIABLE, expressionNode.getType(), false, false);
                        InstructionType operation = InstructionType.valueOf(expressionNode.getBinOp().getArit().getType().name());
                        gc.generate(operation, new Operator3Address(expressionNode.getExp1().getReference()),
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

    public TypeEnum typeFromId(IdDescripcion desc) {
        if (null == desc.getTipoDescripcion()) {
            return null;
        } else {
            switch (desc.getTipoDescripcion()) {
                case dvar:
                    return ((VarDescripcion) desc).getType();
                case dconst:
                    return ((ConstDescripcion) desc).getType();
                case darray:
                    return ((ArrayDescripcion) desc).getType();
                case dtupel:
                    return ((TupelDescripcion) desc).getType();
                case dstring:
                    return TypeEnum.STRING;
                default:
                    return null;
            }
        }
    }

    public int idFromDesc(IdDescripcion desc) {
        if (null == desc.getTipoDescripcion()) {
            return -1;
        } else {
            switch (desc.getTipoDescripcion()) {
                case dvar:
                    return ((VarDescripcion) desc).getVariableNumber();
                case dconst:
                    return ((ConstDescripcion) desc).getVariableNumber();
                case darray:
                    return ((ArrayDescripcion) desc).getVariableNumber();
                case dtupel:
                    return ((TupelDescripcion) desc).getVariableNumber();
                case dstring:
                    return ((StringDescripcion) desc).getVariableNumber();
                default:
                    return -1;
            }
        }
    }

    public boolean handleGestorIdx(GestIdxNode gestIdx, Desplazamiento res) {
        int desp = 0;
        boolean isIdx = false;
        TypeEnum type = TypeEnum.NULL;
        if (gestIdx.getId() != null) {
            IdDescripcion desc = ts.consultaId(gestIdx.getId().getIdentifierLiteral());
            if (desc == null) {
                parser.report_error("No existe el identificador", gestIdx.getId());
            } else {
                if (desc.getTipoDescripcion() == TipoDescripcion.dvar || desc.getTipoDescripcion() == TipoDescripcion.dconst
                        || desc.getTipoDescripcion() == TipoDescripcion.dstring) {
                    if (gestIdx.getGest() != null) {
                        parser.report_error("Se ha intentado indexar una variable que no es un array o una tupla", gestIdx);
                    } else {
                        type = typeFromId(desc);
                    }
                } else if (desc.getTipoDescripcion() == TipoDescripcion.darray || desc.getTipoDescripcion() == TipoDescripcion.dtupel) {
                    if (gestIdx.getGest() != null) {
                        //va a devolver el desplazamiento
                        desp = handleGestor(gestIdx.getGest(), gestIdx.getId().getIdentifierLiteral(), res);
                        isIdx = true;
                        if (desc.getTipoDescripcion() == IdDescripcion.TipoDescripcion.dtupel) {
                            type = res.type;
                        } else {
                            ArrayDescripcion d = (ArrayDescripcion) desc;
                            type = d.getType();
                        }
                    } else {
                        type = typeFromId(desc);
                    }
                } else {
                    parser.report_error("El identificador no tiene un tipo válido", gestIdx);
                    return isIdx;
                }
                int nv = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                gc.generate(InstructionType.CLONE, new Operator3Address(desp, CastType.INT), null, new Operator3Address(nv));
                res.desp = nv;
                res.id = gestIdx.getId().getIdentifierLiteral();
                res.type = type;
            }
        } else {
            parser.report_error("No se ha puesto un identificador", gestIdx);
        }
        return isIdx;
    }

    //va a devolver el desplazamiento relativo para llegar a x dimension o campo
    public int handleGestor(GestorNode node, String id, Desplazamiento res) {
        if (node.getGestArray() != null) {
            ArrayDescripcion a = (ArrayDescripcion) ts.consultaId(id);
            if (a == null) {
                parser.report_error("No existe dicho id", node);
            } else {
                if (!a.isInit()) {
                    parser.report_error("Array no inicializada", node);
                } else {
                    return handleGestArray(node.getGestArray(), id, res);
                }
            }
        } else if (node.getGestTupel() != null) {
            TupelDescripcion a = (TupelDescripcion) ts.consultaId(id);
            if (a == null) {
                parser.report_error("No existe dicho id", node);
            } else {
                if (!a.isInit()) {
                    parser.report_error("Tupla no inicializada", node);
                } else {
                    return handleGestTupel(node.getGestTupel(), id, res);
                }
            }
        }
        return -1;
    }

    public int handleGestArray(GestArrayNode arrayNode, String idArray, Desplazamiento res) {
        if (arrayNode.getExp() != null) {
            handleExpresion(arrayNode.getExp());
            if (arrayNode.getExp().getType() != TypeEnum.INT) {
                parser.report_error("El valor de la dimensión no es de tipo entero", arrayNode.getExp());
                return -1;
            } else {
                if (arrayNode.getGestArray() != null) {
                    int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                    gc.generate(InstructionType.CLONE, new Operator3Address(arrayNode.getExp().getReference()), null, new Operator3Address(var));
                    return handleGestArrayRecur(arrayNode.getGestArray().getGestArray(), idArray, res, var, 1);
                } else {
                    int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                    ArrayDescripcion darray = (ArrayDescripcion) ts.consultaId(idArray);
                    if (darray == null) {
                        parser.report_error("No existe dicho id", arrayNode);
                        return -1;
                    } else {
                        int nbytes = darray.getType().getBytes();
                        gc.generate(InstructionType.MUL, new Operator3Address(arrayNode.getExp().getReference()), new Operator3Address(nbytes, CastType.INT), new Operator3Address(var));
                        return var;
                    }
                }
            }
        } else {
            parser.report_error("No se ha encontrado ninguna expresion para el array", arrayNode);
            return -1;
        }

    }

    public int handleGestArrayRecur(GestArrayNode arrayNode, String idArray, Desplazamiento res, int ref, int posArray) {
        if (arrayNode.getExp() != null) {
            handleExpresion(arrayNode.getExp());
            if (arrayNode.getExp().getType() != TypeEnum.INT) {
                parser.report_error("El valor de la dimensión no es de tipo entero", arrayNode.getExp());
                return -1;
            } else {
                if (arrayNode.getGestArray() != null) {
                    int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                    ArrayList<IndexDescripcion> indices = ts.consultarIndices(idArray);
                    gc.generate(InstructionType.MUL, new Operator3Address(ref), new Operator3Address(indices.get(posArray).getDim()), new Operator3Address(var));
                    int var1 = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                    gc.generate(InstructionType.ADD, new Operator3Address(var), new Operator3Address(arrayNode.getExp().getReference()), new Operator3Address(var1));
                    return handleGestArrayRecur(arrayNode.getGestArray(), idArray, res, var1, posArray + 1);
                } else {
                    int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                    ArrayDescripcion darray = (ArrayDescripcion) ts.consultaId(idArray);
                    if (darray == null) {
                        parser.report_error("No existe dicho id", arrayNode);
                        return -1;
                    } else {
                        int nbytes = darray.getType().getBytes();
                        gc.generate(InstructionType.MUL, new Operator3Address(ref), new Operator3Address(nbytes, CastType.INT), new Operator3Address(var));
                        return var;
                    }
                }
            }
        } else {
            parser.report_error("No se ha encontrado ninguna expresión para el array", arrayNode);
            return -1;
        }
    }

    public int handleGestTupel(GestTupelNode node, String idTupla, Desplazamiento res) {
        IdDescripcion dcampo = ts.consultarCampo(idTupla, node.getIdentifier().getIdentifierLiteral());
        if (dcampo == null) {
            parser.report_error("No existe dicho campo " + node.getIdentifier().getIdentifierLiteral() + " en la tupla " + idTupla, node);
            return -1;
        } else {
            //ahora calcular el desplazamiento
            int desp = 0;
            if (dcampo.getTipoDescripcion() == TipoDescripcion.dcampo) {
                CampoDescripcion aux = (CampoDescripcion) dcampo;
                ArrayList<CampoDescripcion> campos = ts.consultarCampos(idTupla);
                int i = 0;
                for (i = 0; i < campos.size(); i++) {
                    if (campos.get(i).getName().equals(node.getIdentifier().getIdentifierLiteral())) {
                        break;
                    }
                    switch (campos.get(i).getType()) {
                        case INT:
                            desp += TypeEnum.INT.getBytes();
                            break;
                        case CHAR:
                            desp += TypeEnum.CHAR.getBytes();
                            break;
                        case BOOL:
                            desp += TypeEnum.BOOL.getBytes();
                            break;
                        case STRING:
                            desp += TypeEnum.STRING.getBytes();
                            break;
                    }
                }
                res.type = campos.get(i).getType();
                return desp;
            } else {
                parser.report_error("error", node);
                return -1;
            }
        }
    }

    public void handleSimpleValue(SimpleValueNode simpleValue) {
        if (simpleValue.getGestor() != null) {
            Desplazamiento des = new Desplazamiento();
            boolean isIdx = handleGestorIdx(simpleValue.getGestor(), des);
            simpleValue.setType(des.type);
            tempId = des.id;
            int nv = idFromDesc(ts.consultaId(des.id));
            int var = gc.newVar(Variable.TipoVariable.VARIABLE, des.type, false, false);
            if (isIdx) {
                gc.generate(InstructionType.INDVALUE, new Operator3Address(nv), new Operator3Address(des.desp, CastType.INT), new Operator3Address(var));
            } else {
                gc.generate(InstructionType.CLONE, new Operator3Address(idFromDesc(ts.consultaId(des.id))), null, new Operator3Address(var));
            }
        } else if (simpleValue.getSimpl() != null) {
            //?
            //Si no es INT no se puede negar
            handleSimpleValue(simpleValue.getSimpl());
            if (simpleValue.getType() == TypeEnum.INT) {
                //generamos variable para negar adecuada (negaremos una referencia claro)
                int var = gc.newVar(Variable.TipoVariable.VARIABLE, simpleValue.getType(), false, false);
                //generamos la referencia y la negamos
                gc.generate(InstructionType.NEG, new Operator3Address(simpleValue.getReference()), null, new Operator3Address(var));
                //le pasamos la referencia a simpleValue para que la use en futuro handler
                simpleValue.setReference(var);
            } else {
                parser.report_error("No se ha encontrado un valor adecuado, " + simpleValue.getType().getTypeString() + " encontrado y se esperaba " + TypeEnum.INT.getTypeString(), simpleValue);
            }
        } else if (simpleValue.getInstExp() != null) {
            handleInstExp(simpleValue.getInstExp());
            simpleValue.setType(simpleValue.getInstExp().getType());
            if (simpleValue.getType() != null && simpleValue.getType() == TypeEnum.VOID) {
                parser.report_error("Method does not return anything", simpleValue.getInstExp());
            } else if (simpleValue.getType() == TypeEnum.NULL) {
                parser.report_error("Invalid expression", simpleValue.getInstExp());
            } else {
                simpleValue.setReference(simpleValue.getInstExp().getReference());
            }
            simpleValue.setType(simpleValue.getInstExp().getType());
        } else if (simpleValue.getLiteral() != null) {
            simpleValue.setReference(handleLiteral(simpleValue.getLiteral().getLiteral(), simpleValue.getLiteral().getType()));
            simpleValue.setType(simpleValue.getLiteral().getType());
        } else {
            parser.report_error("No suitable development found!", simpleValue);
        }
    }

    public void handleDeclTupel(DeclTupelNode declTupel, IdDescripcion.TipoDescripcion tipo) {
        if (tipo == TipoDescripcion.dconst) {
            parser.syntax_error(new java_cup.runtime.Symbol(39));
        } else {
            if (declTupel.getId() != null) {
                int nVar = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.TUPEL, false, true);
                boolean init = declTupel.getTupeldecl() != null;
                try {
                    ts.poner(declTupel.getId().getIdentifierLiteral(), new TupelDescripcion(nVar, TypeEnum.TUPEL, init));
                } catch (IllegalArgumentException e) {
                    parser.report_error("Ya existe un identificador con este nombre", declTupel.getId());
                    tv.decrement();
                }
                if (declTupel.getParams() != null && !declTupel.getParams().isEmpty() && declTupel.getTupeldecl() != null) {
                    //gestion de los campos
                    ActualParamListNode aux = declTupel.getParams().getActualParamList();
                    while (aux != null) {
                        TypeEnum type = TypeEnum.NULL;
                        if (aux.getParam().getTypeId() != null) {
                            type = aux.getParam().getTypeId().getType();
                            CampoDescripcion dcampo = new CampoDescripcion(type, aux.getParam().getId().getIdentifierLiteral());
                            ts.ponerCampo(declTupel.getId().getIdentifierLiteral(), aux.getParam().getId().getIdentifierLiteral(), dcampo);
                            aux = aux.getActualParamList();
                        } else if (aux.getParam().getSpecialParam() != null) {
                            if (aux.getParam().getSpecialParam().getTypeId().getType() == TypeEnum.ARRAY) {
                                parser.syntax_error(new java_cup.runtime.Symbol(25));
                            } else {//tupel case
                                parser.syntax_error(new java_cup.runtime.Symbol(26));
                            }
                        } else {
                            parser.report_error("No se ha indicado el tipo del campo", aux.getParam());
                        }
                    }
                    if (declTupel.getTupeldecl() != null) {
                        handleTupelDecl(declTupel.getTupeldecl(), declTupel.getId().getIdentifierLiteral());
                    }

                } else {
                    parser.report_error("No se puede declarar una tupla sin campos", declTupel);
                }
            } else {
                parser.report_error("La tupla no tiene un identificador definido!", declTupel);
            }
        }

    }

    public void handleTupelDecl(TupelDeclNode tupelDecl, String id) {
        if (tupelDecl.getInit() != null) {
            handleInitTupel(tupelDecl.getInit(), id);
        } else {
            parser.report_error("La tupla no está inicializada o hay otro error de inicialización!", tupelDecl);
        }
    }

    public void handleInitTupel(InitTupelNode initTupel, String id) {
        if (initTupel.getParams() != null) {
            ArrayList<ExpressionNode> paramsIn = new ArrayList<>();
            handleParamIn(initTupel.getParams(), paramsIn);
            ArrayList<CampoDescripcion> params = ts.consultarCampos(id);
            if (paramsIn.size() != params.size()) {
                parser.report_error("No se han añadido el número correcto de parámetros", initTupel);
            } else {
                int desp = 0;
                IdDescripcion aux = ts.consultaId(id);
                if (aux == null) {
                    parser.report_error("No existe dicho id", initTupel);
                } else {
                    if (aux.getTipoDescripcion() == TipoDescripcion.dtupel) {
                        TupelDescripcion td = (TupelDescripcion) aux;
                        for (int i = 0; i < params.size(); i++) {
                            if (params.get(i).getType() != paramsIn.get(i).getType()) {
                                parser.report_error("No coinciden los tipos del valor pasado con el tipo del campo", initTupel.getParams());
                            } else {
                                //calcular desplazamientos
                                // id[desp] = expressionNode.getReference
                                gc.generate(InstructionType.ASSINDEX, new Operator3Address(paramsIn.get(i).getReference()), new Operator3Address(desp, CastType.INT), new Operator3Address(td.getVariableNumber()));
                                switch (params.get(i).getType()) {
                                    case INT:
                                        desp += TypeEnum.INT.getBytes();
                                        break;
                                    case CHAR:
                                        desp += TypeEnum.CHAR.getBytes();
                                        break;
                                    case BOOL:
                                        desp += TypeEnum.BOOL.getBytes();
                                        break;
                                    case STRING:
                                        desp += TypeEnum.STRING.getBytes();
                                        break;
                                }
                            }
                        }
                        td.setSize(desp);
                    } else {
                        parser.report_error("No es una tupla", initTupel);
                    }
                }
            }
        } else {
            parser.report_error("No se han incorporado valores para assignar a la tupla!", initTupel);
        }
    }

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
            handleParamList(node.getParamList().getActualParamList());
        }

        gc.generate(InstructionType.SKIP, null, null, new Operator3Address(label)); //generamos SKIP para saber donde saltar al hacer call de este procedimiento
        gc.generate(InstructionType.INIT, null, null, new Operator3Address(idProc));

        if (node.getSentenceList() != null) {
            handleSentenceList(node.getSentenceList());
        }

        gc.removeFunctionId(); //quitamos el procedimiento de la pila de procedimientos activos

        gc.generate(InstructionType.RETURN, new Operator3Address(idProc), null, null);

        ts.salirBloque();
    }

    public void handleFunc(FuncNode node) {
        TypeEnum tipo = node.getTypeId().getType();
        String idFunc = node.getId().getIdentifierLiteral();
        gc.addFunctionId(idFunc); //ponemos el procedimiento en la cima de la pila de procedimientos activos
        String label = gc.newLabel();
        int numFunc = gc.newProcedure(idFunc, ts.getActual(), label, 0);
        FuncDescripcion d = new FuncDescripcion(numFunc, tipo);
        try {
            ts.poner(idFunc, d);
        } catch (IllegalArgumentException e) {
            parser.report_error("Función " + idFunc + " ya está definido", node);
            tp.decrement();
        }

        ts.entrarBloque();

        if (node.getParamList() != null && !(node.getParamList().isEmpty())) {
            handleParamList(node.getParamList().getActualParamList());
        }

        gc.generate(InstructionType.SKIP, null, null, new Operator3Address(label)); //generamos SKIP para saber donde saltar al hacer call de este procedimiento
        gc.generate(InstructionType.INIT, null, null, new Operator3Address(idFunc));

        if (node.getSentenceList() != null) {
            handleSentenceList(node.getSentenceList());
        }

        gc.removeFunctionId(); //quitamos el procedimiento de la pila de procedimientos activos

        handleExpresion(node.getExp());

        if (tipo != node.getExp().getType()) {
            parser.report_error("Se intenta devolver un dato cuyo tipo no es el mismo que el definido por la función", node);
        }
        gc.generate(InstructionType.RETURN, new Operator3Address(idFunc), null, new Operator3Address(node.getExp().getReference()));

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
            tv.decrement();
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
                TypeEnum type = node.getExpression().getType();
                if (type != TypeEnum.BOOL) {
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
                    if (d == null) {
                        parser.report_error("No existe dicho id", node.getForInst().getIdentifier());
                    } else {
                        if (node.getForInst().getExpression() == null) {
                            handleSpecialOp(node.getForInst().getSpecialOp(), node.getForInst().getIdentifier());
                        } else { // ID = EXP
                            handleExpresion(node.getExpression());
                            IdDescripcion idd = ts.consultaId(node.getForInst().getIdentifier().getIdentifierLiteral());
                            if (d.getTipoDescripcion() == IdDescripcion.TipoDescripcion.dconst) {
                                parser.report_error("El identificador no es una variable", node.getForInst().getIdentifier());
                            } else {
                                if (idd != null) {
                                    if (node.getForInst().getExpression().getType() != typeFromId(idd)) {
                                        parser.report_error("Se intenta hacer una asignación con diferentes tsb", node.getForInst().getIdentifier());
                                    }
                                    gc.generate(InstructionType.CLONE, new Operator3Address(node.getExpression().getReference()), null, new Operator3Address(idFromDesc(idd)));
                                }
                            }
                        }
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
        if (d == null) {
            parser.report_error("No existe dicho id", node);
        } else {
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
                    gc.generate(InstructionType.CLONE, new Operator3Address(dvar.getVariableNumber()), null, new Operator3Address(result));
                    node.setReference(result);
                } else {
                    parser.report_error("La variable a incrementar/decrementar no es de tipo entero", id);
                }
            } else {
                parser.report_error("El identificador no es una variable", id);
            }
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
            node.setType(TypeEnum.INT);
        } else if (node.getMethodCall() != null) {
            TypeEnum res = handleMethodCall(node.getMethodCall());
            if (node.getMethodCall().getReference() != -1) {
                //FUNC
                node.setReference(node.getMethodCall().getReference());
            }
            node.setType(res);
        } else {
            int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.CHAR, false, false);
            gc.generate(InstructionType.READ, null, null, new Operator3Address(var));
            node.setReference(var);
            node.setType(TypeEnum.CHAR);
        }
    }

    public TypeEnum handleMethodCall(MethodCallNode node) {
        TypeEnum resType = TypeEnum.NULL;
        IdDescripcion d = ts.consultaId(node.getIdentifier().getIdentifierLiteral());
        if (d == null) {
            parser.report_error("El identificador no está definido", node.getIdentifier());
        } else {
            if (d.getTipoDescripcion() == TipoDescripcion.dproc || d.getTipoDescripcion() == TipoDescripcion.dfunc) {
                ArrayList<ArgDescripcion> param = ts.consultarParams(node.getIdentifier().getIdentifierLiteral());
                if (node.getParamIn() != null && !(node.getParamIn().isEmpty())) {
                    ArrayList<ExpressionNode> paramIn = new ArrayList<>();
                    handleParamIn(node.getParamIn(), paramIn);
                    if (param == null || param.size() != paramIn.size()) {
                        parser.report_error("No se han escrito el número de parámetros correspondientes o el procedimiento no necesita parámetros", node.getParamIn());
                    } else {
                        for (int i = 0; i < param.size(); i++) {
                            if (param.get(i).getType() != paramIn.get(i).getType()) {
                                parser.report_error("Uno de los parámetros introducido no tiene el tipo correspondiente", node.getParamIn());
                            }
                            gc.generate(InstructionType.SIMPLEPARAM, null, null, new Operator3Address(paramIn.get(i).getReference()));
                        }
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
                    resType = dfunc.getType();
                } else {
                    gc.generate(InstructionType.CALL, new Operator3Address(node.getIdentifier().getIdentifierLiteral()), null, null);
                    resType = TypeEnum.VOID;
                }
                node.setReference(res);
            } else {
                parser.report_error("Este identificador no corresponde a ningún subprograma", node.getIdentifier());
            }
        }
        return resType;
    }

    public void handleParamIn(ParamInNode node, ArrayList<ExpressionNode> res) {
        handleExpresion(node.getExpression());
        res.add(node.getExpression());
        if (node.getParamIn() != null) {
            handleParamIn(node.getParamIn(), res);
        }
    }

    public void handleAssig(AssigNode assigNode) {
        Desplazamiento desp = new Desplazamiento();
        if (assigNode.getGestIdx() != null) {
            boolean isIdx = handleGestorIdx(assigNode.getGestIdx(), desp);
            IdDescripcion d = ts.consultaId(desp.id);
            if (d == null) {
                parser.report_error("No existe dicho id", assigNode.getGestIdx().getId());
            } else {
                if (assigNode.getExpression() != null) {
                    handleExpresion(assigNode.getExpression());
                    String id = desp.id;
                    switch (d.getTipoDescripcion()) {
                        case dvar:
                            if (desp.type != assigNode.getExpression().getType()) {
                                parser.report_error("El tipo del id no coincide con el de la expresión", assigNode);
                            } else {
                                gc.generate(InstructionType.CLONE, new Operator3Address(assigNode.getExpression().getReference()), null, new Operator3Address(idFromDesc(d)));
                            }
                            break;
                        case dconst:
                            ConstDescripcion dconst = (ConstDescripcion) d;
                            if (dconst.isInit()) {
                                parser.report_error("No se puede hacer una asignación a una variable constante", assigNode);
                            } else {
                                dconst.setInit(true);
                                gc.generate(InstructionType.CLONE, new Operator3Address(assigNode.getExpression().getReference()), null, new Operator3Address(dconst.getVariableNumber()));
                            }

                            break;
                        case darray:
                            if (isIdx) {
                                if (desp.type != assigNode.getExpression().getType()) {
                                    parser.report_error("El tipo de la indexación no coincide con el de la expresión", assigNode);
                                } else {
                                    gc.generate(InstructionType.ASSINDEX, new Operator3Address(assigNode.getExpression().getReference()), new Operator3Address(desp.desp, CastType.INT), new Operator3Address(idFromDesc(d)));
                                }
                            } else {
                                //copiar cada posicion que tiene la array a asignar a cada pos del array de id
                                if (assigNode.getExpression().getType() == TypeEnum.ARRAY) {
                                    boolean iguales = true;
                                    //Id id
                                    ArrayList<IndexDescripcion> idDesc = ts.consultarIndices(id);
                                    //Id Expresion
                                    ArrayList<IndexDescripcion> expDesc = ts.consultarIndices(tempId);
                                    if (idDesc.size() == expDesc.size()) {
                                        for (int i = 0; i < idDesc.size(); i++) {
                                            if (idDesc.get(i).getDim() != expDesc.get(i).getDim()) {
                                                iguales = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (iguales) {
                                        if (assigNode.getExpression().getType() == typeFromId(ts.consultaId(id))) {
                                            //COPIAR DE UNO A OTRO
                                            int nbytes = desp.type.getBytes();
                                            for (int i = 0; i < idDesc.size(); i++) {
                                                //a[desp] = b[desp]
                                                /*
                                                    var0 = desp1 -->b
                                                    var1 = b[var0]
                                                    var2 = desp -->a
                                                    a[var2] = var1
                                                 */
                                                int despB = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                                                gc.generate(InstructionType.CLONE, new Operator3Address(desp.desp + (i * nbytes)), null, new Operator3Address(despB));
                                                int var1 = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                                                gc.generate(InstructionType.INDVALUE, new Operator3Address(tempId), new Operator3Address(despB), new Operator3Address(var1));
                                                int despA = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                                                gc.generate(InstructionType.CLONE, new Operator3Address(desp.desp + (i * nbytes)), null, new Operator3Address(despA));
                                                int var2 = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                                                gc.generate(InstructionType.ASSINDEX, new Operator3Address(id), new Operator3Address(despA), new Operator3Address(var2));
                                                //Copiar de uno a otro
                                                gc.generate(InstructionType.CLONE, new Operator3Address(var1), null, new Operator3Address(var2));

                                            }
                                        } else {
                                            parser.report_error("Los tamaños no coinciden, revisa la asignación de la array", assigNode);
                                        }
                                    } else {
                                        parser.report_error("Los tamaños no coinciden, revisa la asignación de la array", assigNode);
                                    }
                                } else {
                                    parser.report_error("No es una array válida", assigNode);
                                }
                            }
                            break;
                        case dtupel:
                            if (isIdx) {
                                if (desp.type != assigNode.getExpression().getType()) {
                                    parser.report_error("El tipo de la indexación no coincide con el de la expresión", assigNode);
                                } else {
                                    gc.generate(InstructionType.ASSINDEX, new Operator3Address(assigNode.getExpression().getReference()), new Operator3Address(desp.desp, CastType.INT), new Operator3Address(idFromDesc(d)));
                                }
                            } else {
                                //copiar cada campo que tiene la tupla a asignar a cada campo del la tupla de id
                                if (assigNode.getExpression().getType() == TypeEnum.TUPEL) {
                                    //Id id
                                    ArrayList<CampoDescripcion> idDesc = ts.consultarCampos(id);
                                    //Id Expresion
                                    ArrayList<CampoDescripcion> expDesc = ts.consultarCampos(tempId);
                                    if (idDesc.size() == expDesc.size()) {
                                        for (int i = 0; i < idDesc.size(); i++) {
                                            if (idDesc.get(i).getType() == expDesc.get(i).getType()) {
                                                //No estoy seguro, pongo esto temporalmente TODO:Modificar
                                                IdDescripcion expTemp = expDesc.get(i);
                                                CampoDescripcion expCampo = (CampoDescripcion) expTemp;
                                                //Esta vez el de la id
                                                IdDescripcion idTemp = idDesc.get(i);
                                                CampoDescripcion idCampo = (CampoDescripcion) idTemp;
                                                //No está bien creo, el campo deberia tener un nombre bien pero el comment me daba a entender algo mal
                                                int campoExp = idFromDesc(ts.consultarCampo(tempId, expCampo.getName()));
                                                int var = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                                                //En var tenemos la referencia indexada
                                                gc.generate(InstructionType.INDVALUE, new Operator3Address(campoExp), null, new Operator3Address(var));
                                                int var1 = gc.newVar(Variable.TipoVariable.VARIABLE, TypeEnum.INT, false, false);
                                                int campoId = idFromDesc(ts.consultarCampo(tempId, idCampo.getName()));
                                                //La asignación de donde hay que meter el campo
                                                gc.generate(InstructionType.ASSINDEX, new Operator3Address(campoId), null, new Operator3Address(var1));
                                                //Metemos el campo de la exp en la id 
                                                gc.generate(InstructionType.CLONE, new Operator3Address(var), null, new Operator3Address(var1));
                                            } else {
                                                parser.report_error("El tipo del campo no coincide con el del dato", assigNode);
                                                break;
                                            }
                                        }
                                    } else {
                                        parser.report_error("Los tamaños no coinciden, revisa la asignación de la tupla", assigNode);
                                    }
                                } else {
                                    parser.report_error("No es una tupla válida", assigNode);
                                }
                            }
                            break;
                        case dstring:
                            break;
                        default:
                            parser.report_error("No se ha encontrado una descripción adecuada", assigNode);
                            break;
                    }
                } else {
                    parser.report_error("No hay expresión a asignar", assigNode);
                }
            }

        } else {
            parser.report_error("No hay identificador para hacer la asignación", assigNode);
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

}
