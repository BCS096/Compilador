package compilador.sintactic.semantic;

import java.util.ArrayList;
import java.util.Stack;
import types.TypeEnum;
import tablas.*;

public class CodeGeneration3Address {

    private final TablaVariables variableTable;
    private final TablaProcedimientos procedureTable;
    private final Stack<String> procedureStack;
    private final ArrayList<Instruction3Address> instructions;

    public CodeGeneration3Address(TablaVariables variableTable, TablaProcedimientos procedureTable) {
        this.variableTable = variableTable;
        this.procedureTable = procedureTable;
        this.instructions = new ArrayList<>();
        procedureStack = new Stack<>();
    }

    public static final String LABEL = "Label";
    public static final String VAR = "Variable";
    private int labelCounter = 0;

    public String newLabel() {
        return LABEL + labelCounter++;
    }

    public int newVar(Variable.TipoVariable varType, TypeEnum type, boolean isArray, boolean isTupel) {
        variableTable.put(new Variable((VAR + variableTable.getContador()), varType, type, getCurrentFunction(), type.getBytes(), isArray, isTupel));
        //Counter automatically increments one to preapre for next variable. We want the one we just inserted (counter-1)
        return variableTable.getContador() - 1;
    }

    public String getCurrentFunction() {
        if (procedureStack.isEmpty()) {
            // TODO: Could return a fixed id like "global" if needed instead of null
            return null;
        }
        return procedureStack.peek();
    }

    public String removeFunctionId() {
        return procedureStack.pop();
    }

    public void addFunctionId(String id) {
        procedureStack.push(id);
    }

    public int newProcedure(String procedureId, int scope, String firstLabel, int params) {
        procedureTable.put(procedureId, new Procedimiento(scope, firstLabel, params));
        //Counter automatically increments one to preapre for next procedure. We want the one we just inserted (counter-1)
        return procedureTable.getContador() - 1;
    }

    public Procedimiento getProcedure(String procedureId) {
        return procedureTable.get(procedureId);
    }

    public void generate(InstructionType instructionId, Operator3Address operator_1, Operator3Address operator_2, Operator3Address result) {
        Instruction3Address instruction = new Instruction3Address(instructionId, operator_1, operator_2, result);
        this.instructions.add(instruction);
    }

    public ArrayList<Instruction3Address> getInstruccions() {
        return instructions;
    }
}
