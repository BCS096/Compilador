package sintactic.semantic;

import java.util.ArrayList;
import java.util.Stack;
import sintactic.TypeEnum;
import sintactic.tables.*;

public class CodeGeneration3Address {

    private final VariableTable variableTable;
    private final ProcedureTable procedureTable;
    private final Stack<String> procedureStack;
    private final ArrayList<Instruction3Address> instructions;

    public CodeGeneration3Address(VariableTable variableTable, ProcedureTable procedureTable) {
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

    public int newVar(VariableType varType, TypeEnum type, boolean isArray) {
        variableTable.put(new VariableData((VAR + variableTable.getCounter()), varType, type, getCurrentFunction(), type.getBytes(), isArray));
        //Counter automatically increments one to preapre for next variable. We want the one we just inserted (counter-1)
        return variableTable.getCounter() - 1;
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

    public int newProcedure(String procedureId, int scope, String firstLabel) {
        procedureTable.put(procedureId, new ProcedureData(scope, firstLabel, -1, -1));
        //Counter automatically increments one to preapre for next procedure. We want the one we just inserted (counter-1)
        return procedureTable.getCounter() - 1;
    }

    public ProcedureData getProcedure(String procedureId) {
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
