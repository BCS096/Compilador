/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.ensamblado;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import types.TypeEnum;
import compilador.sintactic.semantic.Instruction3Address;
import compilador.sintactic.semantic.InstructionType;
import compilador.sintactic.semantic.Operator3Address;
import compilador.sintactic.semantic.Operator3Address.CastType;
import compilador.sintactic.semantic.Operator3Address.Type;
import tablas.*;
import tablas.Variable.TipoVariable;

/**
 *
 * @author emanu
 */
public class AssemblyGenerator {

    StringBuilder assemblyCode = new StringBuilder();
    TablaVariables tv;
    TablaProcedimientos tp;
    TablaSimbolos ts;
    ArrayList<Instruction3Address> instructions;
    private final String labelSpace = "        ";
    private final String instSpace = " ";
    private final String clearHighRegister = "#$0000FFFF";
    private String filename;
    private final String folder = "pruebas/68K_Tests/";
    private final String extension = ".X68";
    private final String mainName = "main";
    private int procedureCounter = 1; //First procedure as 1
    private final String blockLibrary = "library/BLOCK.X68";
    private final String varLibrary = "library/VAR.X68";
    private final String constLibrary = "library/CONST.X68";
    private final String dmmLibrary = "library/DMM.X68";
    private final String macroLibrary = "library/MACRO.X68";
    private boolean mainCreated = false;
    private final int wordSize = 4; //4 Bytes
    private final int blockWords = 3;
    private final int parameterNumber = 10;
    private final int variableNumber = 20;
    private final int firstParameter = 12;
    private final int parameterSize = wordSize;
    private final int variableSize = wordSize;
    private final int initialSP = 0x01000000;
    private int dx = 0;

    private int actualOffset = 0;
    private final String EBP = "A6";
    private int HN = 1000; //Heap number
    private String HP = "$" + HN;
    private boolean choice = false;
    private ArrayList<String> stringsDone = new ArrayList<>();
    private int contador = 0;
    private int contadorStr = 0;
    private int actualParam = 1;
    private boolean isParam = false;
    private int actualVar = -1;
    private boolean isSimpleP = false;
    private boolean callMade = false;
    private ArrayList<Integer> declaredVars;
    private ArrayList<Integer> paramList;
    private String currentSubProgram = "";
    int randomCounter = 99;
    private boolean isString = false;
    private int arrayCIdx = 0;
    private int tupelCIdx = 0;

    /**
     *
     * @param tv
     * @param tp
     * @param expansionTable
     * @param symbolTable We need all the tables for the code generation process
     * @param instructions
     */
    public AssemblyGenerator(String filename, TablaSimbolos symbolTable, TablaVariables tv, TablaProcedimientos tp, ArrayList<Instruction3Address> instructions) {
        this.declaredVars = new ArrayList<>();
        this.paramList = new ArrayList<>();
        this.filename = filename;
        this.ts = symbolTable;
        this.tv = tv;
        this.tp = tp;
        this.instructions = instructions;

    }

    public void mainMake() {
        //TODO: Generate main and then warning and errors (Overflow, Carry...)
        assemblyCode.append(labelSpace + "INCLUDE " + instSpace + macroLibrary + '\n');
        assemblyCode.append(labelSpace + "INCLUDE " + instSpace + constLibrary + '\n');
        assemblyCode.append(labelSpace + "INCLUDE " + instSpace + varLibrary + '\n');
//        assemblyCode.append(labelSpace + "INCLUDE " + instSpace + dmmLibrary + '\n');
//        assemblyCode.append(labelSpace + "INCLUDE " + instSpace + blockLibrary + '\n');
        assemblyCode.append(";-----------------------------------------------------------\n");
        assemblyCode.append(";-----------------------------------------------------------\n");
        assemblyCode.append("; Initial program lines, main                               \n");//TODO: Main
        assemblyCode.append(";-----------------------------------------------------------\n");
        assemblyCode.append("; Labels to memory space reserved for variables:            \n");
        assemblyCode.append(";-----------------------------------------------------------\n");
        //Initiate all variables in memory

        for (int i = 0; i < tv.getContador(); i++) {
            if (tv.get(i).getTipo() == TypeEnum.STRING) {
                assemblyCode.append(tv.get(i).getId() + labelSpace + "DC.B" + instSpace + getStringValue(i) + ",0\n");
            } else {
                if (tv.get(i).isArray()) {
                    int size = true ? 15 : tv.get(i).getBytes();
                    assemblyCode.append(tv.get(i).getId() + labelSpace + "DS.L" + instSpace + size + "\n");
                } else if (tv.get(i).isTupel()) {
                    int size = tv.get(i).getBytes();
                    assemblyCode.append(tv.get(i).getId() + labelSpace + "DS.L" + instSpace + size + "\n");
                } else {
                    assemblyCode.append(tv.get(i).getId() + labelSpace + "DS.L" + instSpace + "1\n");
                }
            }
        }
        //strings+otros hacer
        for (int i = 0; i < 5; i++) {
            assemblyCode.append("SParam" + i + labelSpace + "DS.B" + instSpace + "128\n");
            assemblyCode.append(labelSpace + "DC.B" + instSpace + "0\n");
        }
        for (int i = 0; i < 10; i++) {
            assemblyCode.append("Param" + i + labelSpace + "DS.L" + instSpace + "1\n");
        }
//        for (int i = 7; i < 10; i++) {
//            assemblyCode.append("Param" + i + labelSpace + "DS.L" + instSpace + "1\n");
//        }

        completeProcedureTable();
        updateVariableTable();
        System.out.println("Te imprimo las variables? (true/false)");
        choice = false;//Scanner scan = new Scanner(System.in);
        //choice = scan.nextBoolean();
        if (choice) {
            for (int i = 0; i < tv.getContador(); i++) {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                System.out.println("El identificador: " + tv.get(i).getId() + ", el nombre: " + tv.get(i).getIdProcedimiento());
                if (tp.get(tv.get(i).getIdProcedimiento()) != null) {
                    System.out.println("La profundidad de la variable es: " + tp.get(tv.get(i).getIdProcedimiento()).getAmbito());
                } else {
                    System.out.println("No hay profundidad de procedure, o sea que profundidad: 0");
                }
                System.out.println("");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            }
        } else {
        }
        assemblyCode.append(";-----------------------------------------------------------\n");
        assemblyCode.append(labelSpace + "DS.W" + instSpace + "0\n");
        assemblyCode.append(";-----------------------------------------------------------\n");
        assemblyCode.append("globals:\n");
        boolean moreGlobals = true;
        System.out.println("Te imprimo las variables finales? (true/false)");
        //choice = scan.nextBoolean();
        choice = false;
        for (Instruction3Address instruction : instructions) {
            assemblyCode.append(header(instruction) + '\n');
            //If main not created and we don't have to do skip...
            if (!mainCreated && moreGlobals) {
                if (instruction.getInstructionType() != InstructionType.CLONE) {
                    moreGlobals = false;
                    assemblyCode.append(labelSpace + "JMP" + instSpace + mainName + " ; Declared all globals\n");
                    makeOperation(instruction);
                } else {
                    makeOperation(instruction);
                }

            } else {

                makeOperation(instruction);
            }

        }

        //TODO: Generate ending
        assemblyCode.append(labelSpace
                + "END" + instSpace + "globals");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(folder + filename.substring(0, filename.length() - 4) + extension)))) {
            bufferedWriter.write(assemblyCode.toString());
            //For overwritting avoidance
            Thread.sleep(2000);
        } catch (IOException ex) {
            System.out.println("No file was generated, something went wrong...");
            Logger.getLogger(AssemblyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AssemblyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//TODO: Give 3 code address operand as parameter, make assembly operand
//Also fix all values to references (working with SP aka A7)
    public void makeOperation(Instruction3Address instruction) {

        switch (instruction.getInstructionType()) {
            case CLONE:
                //Case A=B, Valor in A, store in C ta mal
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                if (((tv.get(instruction.getOperators()[0].getReference()).getTipo() == TypeEnum.STRING) && (tv.get(instruction.getOperators()[2].getReference()).getTipo() == TypeEnum.STRING))
                        || (instruction.getOperators()[0].getType() != Type.literal && tv.get(instruction.getOperators()[2].getReference()).getTipo() == TypeEnum.STRING)) {
                    isString = true;
                }
                STORE("D0", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), instruction.getOperators()[2].getReference());
                isString = false;
                break;
            case ADD:

                //Assuming A+B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "ADDM" + instSpace + "D0, D1\n");
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case SUB:
                //Assuming A-B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "SUBM" + instSpace + "D0, D1\n");
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case MUL:
                //Assuming A*B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "MULTM" + instSpace + "D0, D1\n");
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case DIV:
                //Assuming A/B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "DIVM" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "AND.L" + instSpace + clearHighRegister + ", D1\n"); //Remove remainder
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case MOD:
                //Assuming A/B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "MODM" + instSpace + "D1, D0\n"); //In this macro we clear division result and swap inside it
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case NEG:
                //A = -b
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "NEGM" + instSpace + "D1"); //If we already use D1 for result, optimal to get the value inside it
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), instruction.getOperators()[2].getReference());
                break;
            case AND:
                //Assuming A & B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "ANDM" + instSpace + "D0, D1\n");
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case OR:
                //Assuming A | B, store in C
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "ORM" + instSpace + "D0, D1\n");
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(),
                        instruction.getOperators()[2].getReference());
                break;
            case NOT:
                //a = NOT b
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "NOTM" + instSpace + "D1"); //If we already use D1 for result, optimal to get the value inside it
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), instruction.getOperators()[2].getReference());
                break;
            case INDVALUE:
                //C = A[B]
                //B might be inmediate (assuming decimal value)
                //Array always stored before, if we acces indexed value
//                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                assemblyCode.append(labelSpace + "LEA" + instSpace + '(' + tv.get(instruction.getOperators()[0].getReference()).getId() + "), A0\n");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D0");
                assemblyCode.append(labelSpace + "ADD.L" + instSpace + "D0, A0 ; D0 = @B[C]\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0), D0 ; D0 = B[C]\n");
                STORE("D0", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), instruction.getOperators()[2].getReference());
                break;
            case ASSINDEX:

                //C[B] = A, if C,B,A = OP1,OP2,OP3
//                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                assemblyCode.append(labelSpace + "LEA" + instSpace + '(' + tv.get(instruction.getOperators()[2].getReference()).getId() + "), A0\n");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D0");
                assemblyCode.append(labelSpace + "ADD.L" + instSpace + "D0, A0 ; D0 = @B[C]\n");
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D2");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "D2, (A0) ; Store C IN A[B]\n");
                break;
            case SKIP:
                //assemblyCode.append(tp.get(instruction.getOperators()[2].getLabel()).getStartLabel() + ":\n");
                assemblyCode.append(instruction.getOperators()[2].getLabel() + ":\n");
                break;
            case GOTO:
                assemblyCode.append(labelSpace + "JMP" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case IFLT:
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "CMP.L" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "BGE" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case IFLE:
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "CMP.L" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "BGT" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case IFGT:
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "CMP.L" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "BLE" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case IFGE:
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "CMP.L" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "BLT" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case IFEQ:
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "CMP.L" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "BNE" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case IFNE:
                LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(), tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D0");
                LOAD(instruction.getOperators()[1], instruction.getOperators()[1].getReference(), tv.get(instruction.getOperators()[1].getReference()).getIdProcedimiento(), "D1");
                assemblyCode.append(labelSpace + "CMP.L" + instSpace + "D1, D0\n");
                assemblyCode.append(labelSpace + "BEQ" + instSpace + instruction.getOperators()[2].getLabel() + '\n');
                break;
            case PMB:
                //Preamble
                if (instruction.getOperators()[2].getLabel() == (mainName)) {
                    assemblyCode.append(";Preamble of MAIN ignored\n");
                    break;
                } else {
                    //We already have the parameters and return address. We just put in the DISP and BP
                    int prof4x = tp.get(instruction.getOperators()[2].getLabel()).getAmbito() * 4;
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "DISP, A0\n");
                    //Antic DISP(prof)
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + prof4x + "(A0), -(A7)\n");
                    //Antic BP
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + EBP + ", -(A7)\n");
                    //Update BP
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A7, " + EBP + " ;BP = SP\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + EBP + ", " + (prof4x - 4) + "(A7) ; DISP(prof) = BP\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + EBP + ", D0 ; D0=EBP\n");
                    //assemblyCode.append(labelSpace + "SUB.L" + instSpace + "#" + (variableNumber * variableSize) + ", D0 ;\n");
                    //assemblyCode.append(labelSpace + "SUB.L" + instSpace + '#' + "VARSIZE, A7 ; SP changed to assign Variable Space\n");
                    ArrayList<ArgDescripcion> params = ts.consultarParams(instruction.getOperators()[2].getLabel(), null);
                    int contadorStrings = 0;
                    int contadorVars = 0;
                    for (int i = 0; i < params.size(); i++) {
                        VarDescripcion desc = (VarDescripcion) ts.consultaId(params.get(i).getName());
                        if (desc.getType() == TypeEnum.STRING) {
                            assemblyCode.append(labelSpace + "LEA" + instSpace + "SParam" + contadorStrings + ", " + "A0 ; Param gest\n");
                            assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + desc.getVariableNumber() + ", " + "A1 ; Param gest\n");
                            assemblyCode.append(".loop" + contadorStrings + ':' + '\n');
                            assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "(A0)+, (A1)+ \n");
                            assemblyCode.append(labelSpace + "TST.B (A0) \n");
                            assemblyCode.append(labelSpace + "BNE .loop" + contadorStrings + '\n');
                            assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "#0, (A1)+ \n");
                            contadorStrings++;
                        } else {
                            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(Param" + contadorVars++ + "), " + "(Variable" + desc.getVariableNumber() + ") ; Param gest\n");
                        }
                    }
                }
                paramList = new ArrayList<>();
                break;
            case RETURN:
                if (instruction.getOperators()[0].getLabel() == mainName) {
                    assemblyCode.append(labelSpace + "SIMHALT\n");
                    break;
                }
                int prof4x = tp.get(instruction.getOperators()[0].getLabel()).getAmbito() * 4;
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + EBP + ", A7 ; SP = BP, return to state before PMB\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A7)+, " + EBP + " ; BP = old BP\n");
                assemblyCode.append(labelSpace + "LEA" + instSpace + "DISP, A0 ; A0 = @DISP\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A7)+, " + prof4x + "(A0) ; DISP[prof] = old value\n");
                if (instruction.getOperators()[2] != null) {
                    LOAD(instruction.getOperators()[2], instruction.getOperators()[2].getReference(), tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), "D5");
                }
                assemblyCode.append(labelSpace + "RTS ; Return\n");
                break;
            case CALL:
                actualParam = 1;
                if (instruction.getOperators()[0].getLabel() == mainName) { //Main case
                    makeMain();
                    mainCreated = true;
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A7, " + EBP + '\n'); //Mover la SP a EBP
//                    assemblyCode.append(labelSpace + "SUB.L" + instSpace + '#' + (variableSize * variableNumber) + ", " + EBP + " ; Add variables reserved to main \n");
//                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#$0F0F0F0F, -(A7) \n"); //o bien 4 más de offset???
                    actualOffset = 0;//parameterNumber * wordSize + 12 + wordSize; //10*4 right now, 12 are the 3 vars (DISP, RET, BP), and we do --, so first is wrong (wordSize corrects it)
                    break;
                }
                Procedimiento procedure = tp.get(instruction.getOperators()[0].getLabel());
                currentSubProgram = instruction.getOperators()[0].getLabel();
                String label = procedure.getEtiquetaInicial();

                int numberParameters = procedure.getNumberParameters(); //How many parameters (max ->parameterNumber)
                assemblyCode.append(labelSpace + "JSR" + instSpace + label + " ; GOTO " + label + "\n"); //Make the jump, we have parameters in stack (From SIMPLEPARAM)
                if (instruction.getOperators()[2] != null) {
                    //D5 used for returning values
                    STORE("D5", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), instruction.getOperators()[2].getReference());
                }

                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + EBP + ", A7\n");
                actualOffset = parameterNumber * wordSize + 12 + wordSize; //Restore offset for any later calls
                contador = 0;
                contadorStr = 0;
                break;
            case SIMPLEPARAM:
                int variableId = instruction.getOperators()[2].getReference();
                String procedureId = tv.get(variableId).getIdProcedimiento();
                isParam = true;
                if (actualParam == 1 && mainCreated) {//caso primer param, hay que modificar EBP, SI MAIN ESTÁ CREADO
                    assemblyCode.append(labelSpace + "SUB.L" + instSpace + '#' + ((parameterNumber * parameterSize)) + ", " + EBP + "\n"); //sumar a EBP la zona params
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + EBP + ", A7\n");
                }
                isSimpleP = true;
                LOAD(instruction.getOperators()[2], variableId, procedureId, "D0");
                isSimpleP = false;
                isParam = false;
                if (true) {
                    if (tv.get(variableId).getTipo() == TypeEnum.STRING) {

                    } else {
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "D0, (Variable" + variableId + ") \n");
                    }
                    paramList.add(variableId);
                }
                if (choice) {
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("El identificador: " + tv.get(variableId).getId() + ", el nombre: " + tv.get(variableId).getIdProcedimiento());
                    if (tp.get(tv.get(variableId).getIdProcedimiento()) != null) {
                        System.out.println("La profundidad de la variable es: " + tp.get(tv.get(variableId).getIdProcedimiento()).getAmbito());
                    } else {
                        System.out.println("No hay profundidad de procedure, o sea que profundidad: 0");
                    }
                    System.out.println("");
                    System.out.println("La variable tiene un offset de: " + tv.get(variableId).getOffset());
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                }
                if (tv.get(variableId).getTipo() == TypeEnum.STRING) {
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "SParam" + contadorStr + ", " + "A1 ; Param gest\n");
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", " + "A0 ; Param gest\n");
                    assemblyCode.append(".loop" + randomCounter + ": \n");
                    assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "(A0)+, (A1)+ \n");
                    assemblyCode.append(labelSpace + "TST.B (A0) \n");
                    assemblyCode.append(labelSpace + "BNE .loop" + randomCounter-- + '\n');
                    assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "#0, (A1)+ \n");
                } else {
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + '(' + "Variable" + variableId + "), " + '(' + "Param" + contador + ") ; Param\n");
                }
                contador++;
                break;
            case READ:
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#5, D0 ; Prepare read\n");
                assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15 ; Expect input\n");
                assemblyCode.append(labelSpace + "AND.L" + instSpace + "#$00FF, D1 ; Mask upper word (we read char = 2 bytes)\n");
                STORE("D1", tv.get(instruction.getOperators()[2].getReference()).getIdProcedimiento(), instruction.getOperators()[2].getReference());
                break;
            case PRINT:
                Variable variable = tv.get(instruction.getOperators()[0].getReference());

                if (variable.getTipo() == TypeEnum.STRING) {
                    assemblyCode.append(labelSpace + "LEA" + labelSpace + "Variable" + instruction.getOperators()[0].getReference() + ", A0 ; Cargar la string\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A0, A1 ; Ready text\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#14, D0 ; Prepare display\n");
                    assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15\n ; Expect screen visualization\n");
                } else if (variable.getTipo() == TypeEnum.CHAR) {
                    LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(),
                            tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D1");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#6, D0 ; Prepare display\n");
                    assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15\n ; Expect screen visualization\n");
                } else if (variable.getTipo() == TypeEnum.BOOL || variable.getTipo() == TypeEnum.INT) {
                    LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(),
                            tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D1");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#3, D0 ; Prepare display\n");
                    assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15\n ; Expect screen visualization\n");

                }
                break;
            case PRINTLN:
                variable = tv.get(instruction.getOperators()[0].getReference());
                if (1 == 0) {
                } else {
                    if (variable.getTipo() == TypeEnum.STRING) {
                        assemblyCode.append(labelSpace + "LEA" + labelSpace + "Variable" + instruction.getOperators()[0].getReference() + ", A0 ; Cargar la string\n");
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A0, A1 ; Ready text\n");
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#14, D0 ; Prepare display\n");
                        assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15\n ; Expect screen visualization\n");
                    } else if (variable.getTipo() == TypeEnum.CHAR) {
                        LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(),
                                tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D1");
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#6, D0 ; Prepare display\n");
                        assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15\n ; Expect screen visualization\n");
                    } else if (variable.getTipo() == TypeEnum.BOOL || variable.getTipo() == TypeEnum.INT) {
                        LOAD(instruction.getOperators()[0], instruction.getOperators()[0].getReference(),
                                tv.get(instruction.getOperators()[0].getReference()).getIdProcedimiento(), "D1");
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#3, D0 ; Prepare display\n");
                        assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15\n ; Expect screen visualization\n");
                    }
                }

                assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "#11, D0 ; Next line prepare\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#$00FF, D1 ; Request current coordinates\n");
                assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15 ; Get them in D1.L (HIGH=COL, LOW = ROW)\n");
                assemblyCode.append(labelSpace + "AND.L" + instSpace + "#$00FF, D1 ; We want always column = 0\n");
                assemblyCode.append(labelSpace + "ADDQ.B" + instSpace + "#1, D1 ; We increment the current row by 1\n");
                assemblyCode.append(labelSpace + "TRAP" + instSpace + "#15 ; Set new coordinates (next line ready)\n");
                break;
        }
    }

    //TODO: Better view (when no op1, or op2, or op3
    private String header(Instruction3Address instruction) {
        boolean op1, op2, op3;
        op1 = op2 = op3 = false;
        String result = "; Instruction of type: " + instruction.getInstructionType().toString() + "\n;";
        if (instruction.getOperators()[0] != null) {
            result = result + "Operator 1 -> " + (instruction.getOperators()[0].getReference());
            op1 = true;
        }
        if (instruction.getOperators()[1] != null) {
            result = result + ", Operator 2 -> " + instruction.getOperators()[1];
            op2 = true;
        }
        if (instruction.getOperators()[2] != null) {
            result = result + ", Store in -> " + (instruction.getOperators()[2].getReference());
            op3 = true;
        }
        return result + '\n';
    }

    private void LOAD(Operator3Address literal, int variableId, String procedureId, String register) {
        Variable variable = tv.get(variableId);
        System.out.println("LOADING... -> " + variable.toString());
        char size = 'L';
        //Default scope of both:
        int profx = 0;
        int profp = 0;
        if (tp.get(variable.getIdProcedimiento()) != null) {
            profx = tp.get(variable.getIdProcedimiento()).getAmbito();
            profp = tp.get(procedureId).getAmbito();
        }
        int dx = tv.get(variableId).getOffset();
        if (isParam) {
            dx = tv.get(variableId).getBytes() * actualParam; //12 = DISP (4) + BP (4) + RETURN (4)
            actualParam++;
        }
        if (literal.getType() == Type.literal) //If we have scalar value
        {
            switch (literal.getCastType()) {
                case INT:
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + '#' + ((int) literal.getLiteral()) + ", " + register + " ; Load variable\n");
                    break;
                case CHAR:
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "#'" + ((char) literal.getLiteral()) + "', " + register + " ; Load variable\n");
                    break;
                case BOOL:
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + '#' + ((boolean) literal.getLiteral() ? '1' : '0') + ", " + register + " ; Load variable\n");
                    break;
                case STRING:
//                    assemblyCode.append(labelSpace + "DS.W 0\n");
//                    assemblyCode.append(".temp" + contador + labelSpace + "DC.B" + instSpace + ((String) literal.getLiteral()).replace('"', '\'') + ", 0 ; Inmediate string\n");
//                    assemblyCode.append(labelSpace + "DS.W 0\n");
//                    stringsDone.add(variable.getId());
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", A0\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A0, " + register + " ; Load variable\n");
                    break;
                default:
                    throw new UnsupportedOperationException("Trying to load something that is not a variable!");
            }
        } else if (profx == profp && dx < 0) { //Si profundidad de la variable es la misma que el procedure y está en negativo por debajo de BlockPointer
            if (variable.isArray()) {
                assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", A0 ; Load local variable\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A0, " + register + " ; Load local variable\n");
            } else {
                assemblyCode.append(labelSpace + "MOVE." + size + instSpace + '(' + "Variable" + variableId + "), " + register + " ; Load local variable\n");
            }
        } else if (profx == profp && dx > 0) { //Si profundidad de la variable es la misma que el procedure y está en positivo por encima de BlockPointer
            //dx = tv.get(variableId).getOffset() + 4;
            if (true) {
                if (!(variable.getTipo() == TypeEnum.STRING)) {
                    assemblyCode.append(labelSpace + "MOVE." + size + instSpace + "(Variable" + variableId + "), " + register + " ; Load local param\n");
                } else {
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", A0 ; Load local param\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A0, " + register + " ; Load it to register \n");
                }
            } else {
                if (isSimpleP) {
                    assemblyCode.append(labelSpace + "MOVE." + size + instSpace + EBP + ", D0\n");
                    //assemblyCode.append(labelSpace + "ADD.L" + instSpace + '#' + dx + ", D0\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "D0, A0 ; A0 contains store address\n");
                    int dxVar = 0; //offset de la variable local en memoria (caso parametro local claro)
                    if (tp.get(procedureId) == null) {
                        dxVar = initialSP;
                        dxVar += tv.get(variableId).getOffset() - 4;
                    } else {
                        dxVar = tv.get(variableId).getOffset();
                        dxVar += (parameterNumber * parameterSize) + (variableNumber * variableSize) + 4; //antic BP
                    }
                    assemblyCode.append(labelSpace + "CLR.L" + instSpace + "D1 ; Get D1 cleaned\n");
                    //modify to hex, 99999 is FFFFF            
                    assemblyCode.append(labelSpace + "ADD.L" + instSpace + "#" + dxVar + ", D1 ; Get variable address into D1\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "D1, A1 ; Get variable address into A1\n");
                    if (register.contains("D")) {
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A1, (A0) ; Get parameter\n");
                    } else {
                        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A1, (" + register + ") ; Get parameter\n");
                    }//revisar loop del sumTo HACE LO MISMO TODO EL RATO CHECK EVERY STEP AND THAT IS DONE
                } else {
                    assemblyCode.append(labelSpace + "MOVE." + size + instSpace + (dx + 8) + '(' + EBP + "), A0 ; Load local param @\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0), " + register + " ; Load local param \n");
                }
            }
        } else if (profx < profp && dx < 0) {
            assemblyCode.append(labelSpace + "LEA" + instSpace + "DISP, A0 ; @DISP in A0\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + profx + "(A0), A0 ; DISP[profx] in A0, it is BPx value\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + dx + "(A0), " + register + " ; Load outsider variable in " + register + '\n');
        } else if (profx < profp && dx > 0) {
            assemblyCode.append(labelSpace + "LEA" + instSpace + "DISP, A0 ; @DISP in A0\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + profx + "(A0), A0 ; DISP[profx] in A0, it is BPx value\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0), A0\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0), " + register + " ; Load outsider parameter in " + register + '\n');
        } else {
            if (dx == 0) { //es una string, chapó
                assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", " + "A0 ; Param gest\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A0, " + register + " ; Param gest\n");
            } else {
                throw new UnsupportedOperationException("Variable or parameter not valid for loading!");
            }
        }
    }

    private void STORE(String register, String procedureId, int variableId) {
//add (variableNumber * variableSize) to vars, so go back to good pos
        Variable variable = tv.get(variableId);
        System.out.println("STORING... -> " + variable.toString());
        char size = 'L';
        //TODO:
        //If it is variable, increment BP and VARSIZE of corresponding method. 
        //If it is parameter, increment HP, store if necessary.
        //Default scope of both:
        int profx = 0;
        int profp = 0;
        boolean notGlobal = tp.get(variable.getIdProcedimiento()) != null;
        if (notGlobal) {
            profx = tp.get(variable.getIdProcedimiento()).getAmbito();
            profp = tp.get(procedureId).getAmbito();
        }
        //OFFSET DE LA VARIABLE
        int dx = variable.getOffset();
        if (isParam) {
            dx = 12 + tv.get(variableId).getBytes() * actualParam;
            actualParam++;
        }
        if (profx == profp && dx < 0) { //Local variable
            //Look up variable space, if sums correctly and add all the space to A7 and we win jejeje
            if (true) {
                if (variable.isArray()) {
                    int sizeArray = tv.get(variableId).getBytes();
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + "A0 ; Store local variable\n");
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", " + "A1 ; Store local variable\n");
                    assemblyCode.append(labelSpace + "CLR.L" + instSpace + "D1\n");
                    assemblyCode.append(labelSpace + "CLR.L" + instSpace + "D2\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(Variable" + sizeArray + "), D2 \n");
                    assemblyCode.append(labelSpace + "DIVS" + instSpace + "#4, D2 \n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "D2, D1 \n");
                    assemblyCode.append(".arrayCopy" + arrayCIdx + ":\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0)+, " + "(A1)+ ; Store local variable\n");
                    assemblyCode.append(labelSpace + "DBRA" + instSpace + "D1, .arrayCopy" + arrayCIdx++ + '\n');
                } else if(variable.isTupel()){
                    int sizeArray = tv.get(variableId).getBytes();
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + "A0 ; Store local variable\n");
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", " + "A1 ; Store local variable\n");
                    assemblyCode.append(labelSpace + "CLR.L" + instSpace + "D1\n");
                    assemblyCode.append(labelSpace + "CLR.L" + instSpace + "D2\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + '#' + sizeArray + ", D2 \n");
                    assemblyCode.append(labelSpace + "DIVS" + instSpace + "#4, D2 \n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "D2, D1 \n");
                    assemblyCode.append(".tupelCopy" + tupelCIdx + ":\n");
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0)+, " + "(A1)+ ; Store local variable\n");
                    assemblyCode.append(labelSpace + "DBRA" + instSpace + "D1, .arrayCopy" + arrayCIdx++ + '\n');
                }else {
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + "(Variable" + variableId + ") ; Store local variable\n");
                }
            } else {
                if (declaredVars.contains(variableId)) {
                    //Not bien, no se como pero hay que modificar para que las vars se modifiquen en su contexto revisar oduisgnwiebnowiqgenbwoenv
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + dx + '(' + EBP + ") ; Store local variable\n");
                } else {
                    declaredVars.add(variableId);
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + "-(A7) ; Store local variable\n");
                }
            }
            if (notGlobal) {
                tp.get(variable.getIdProcedimiento()).setLocalVariablesSize(tp.get(variable.getIdProcedimiento()).getLocalVariablesSize() + tv.get(variableId).getBytes());
            }
        } else if (profx == profp && dx > 0) { //Local parameter
            if (true) {
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + "(Variable" + variableId + ") ; Store local parameter\n");
            } else {
                assemblyCode.append(labelSpace + "MOVE." + size + instSpace + dx + '(' + EBP + "), A0 ; A0 contains parameter @\n");
                assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", (A0) ; Store local parameter\n");
            }
        } else if (profx < profp && dx < 0) { //Foreign variable
            assemblyCode.append(labelSpace + "LEA" + instSpace + "DISP, A0 ; Get DISP @\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + profx + "(A0), A0 ; A0 contains BPx, from DISP[profx]\n");
            assemblyCode.append(labelSpace + "MOVE.L" + register + ", " + dx + "(A0) ; Store foreign variable\n");
        } else if (profx < profp && dx > 0) { //Foreign parameter
            assemblyCode.append(labelSpace + "LEA" + instSpace + "DISP, A0 ; @DISP in A0\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + profx + "(A0), A0 ; DISP[profx] in A0, it is BPx value\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "(A0), A0\n");
            assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", (A0) ; Store foreign parameter\n");
        } else {
            if (dx == 0) {
                if (isString) {
                    assemblyCode.append(labelSpace + "MOVE.L" + instSpace + register + ", " + "A0 ; Param gest\n");
                    assemblyCode.append(labelSpace + "LEA" + instSpace + "Variable" + variableId + ", " + "A1 ; Param gest\n");
                    assemblyCode.append(".loop" + randomCounter + ':' + '\n');
                    assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "(A0)+, (A1)+ \n");
                    assemblyCode.append(labelSpace + "TST.B (A0) \n");
                    assemblyCode.append(labelSpace + "BNE .loop" + randomCounter-- + '\n');
                    assemblyCode.append(labelSpace + "MOVE.B" + instSpace + "#0, (A1)+ \n");
                }
            } else {
                throw new UnsupportedOperationException("Variable or parameter not valid for storing!");
            }
        }
    }

    private void completeProcedureTable() {
        /*
        forall p = 1..np
        TP(np).ocupVL = 0
        forall x = 1..nv
        if TV(x).variant = variable
            p = TV(x).proc
            ocupx = TV(x).ocup
            TP(p).ocupVL = TP(p).ocupVL + ocupx
            TV(x).desp = TP(p).ocupVL
        end if
         */
        for (String procedureId : tp.getKeys()) {
            Procedimiento procedure = tp.get(procedureId);
            procedure.setLocalVariablesSize(0);
            tp.put(procedureId, procedure);
        }
    }

    private void updateVariableTable() {
        String actualProcedure = "noProc";
        boolean tvParam = false;
        int currentParam = 1;
        for (int variableId = 0; variableId < tv.getContador(); variableId++) {
            String procedureId = tv.get(variableId).getIdProcedimiento();
            //COTI
            if (tv.get(variableId).getTipoVariable() == Variable.TipoVariable.VARIABLE && tv.get(variableId).getTipo() != TypeEnum.STRING) {
                int variableSize = tv.get(variableId).getBytes();
                //Update procedure? (when not main)
                if (tp.get(procedureId) != null) {
                    tp.get(procedureId).setLocalVariablesSize(variableSize + tp.get(procedureId).getLocalVariablesSize());
                }
                if (procedureId == actualProcedure) {
                    tv.get(variableId).setOffset(actualVar-- * (variableSize < 4 ? 4 : variableSize)); //Global variables be one after another in memory}
                } else if (actualProcedure == "noProc") {
                    actualProcedure = procedureId;
                    tv.get(variableId).setOffset(actualVar-- * (variableSize < 4 ? 4 : variableSize));
                } else {
                    actualProcedure = null;
                    actualVar = -1;
                    tv.get(variableId).setOffset(actualVar-- * (variableSize < 4 ? 4 : variableSize));
                }
                if (actualProcedure == null) {
                    System.out.println(tv.get(variableId) + ", de offset -> " + tv.get(variableId).getOffset() + '\n');
                }
            } else if (tv.get(variableId).getTipoVariable() == Variable.TipoVariable.PARAM) {
                int variableSize = tv.get(variableId).getBytes();
                tvParam = true;
                tv.get(variableId).setOffset(currentParam * (variableSize < 4 ? 2 : variableSize));
                if (currentParam <= tp.get(procedureId).getNumberParameters()) {
                    currentParam++;
                } else {
                    currentParam = 1;
                }
            }
        }
    }

    private void makeMain() {
        assemblyCode.append(mainName + ':' + "                                                       \n");
        //We initialize EBP, using A6 register for it
//        assemblyCode.append(labelSpace + "MOVE.L" + instSpace + "A7, " + EBP + '\n');

    }

    private String getStringValue(int i) {
        for (Instruction3Address instruction : instructions) {
            if (instruction.getInstructionType() == InstructionType.CLONE) {
                if (instruction.getOperators()[2].getReference() == i) {
                    if (instruction.getOperators()[0].getLiteral() == null) {
                        return getStringValue(instruction.getOperators()[0].getReference());
                    }
                    return ((String) instruction.getOperators()[0].getLiteral()).replace('"', '\'');
                }
            }
        }
        return "'Sorry, no String found at given variable'";
    }
}
