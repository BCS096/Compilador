package compilador.sintactic.semantic;

public class Instruction3Address {

    private final InstructionType instructionType;
    private final Operator3Address[] operators;

    public Instruction3Address(InstructionType instruction, Operator3Address operator1, Operator3Address operator2, Operator3Address result) {
        this.operators = new Operator3Address[3];
        operators[0] = operator1;
        operators[1] = operator2;
        operators[2] = result;
        this.instructionType = instruction;
    }

    public Operator3Address[] getOperators(){
        return this.operators;
    }
    
    public InstructionType getInstructionType(){
        return this.instructionType;
    }
    
    @Override
    public String toString() {
        return instructionType.getInstructionCode() + " | "
                + ((operators[0] != null) ? operators[0].toString() : "null") + " | "
                + ((operators[1] != null) ? operators[1].toString() : "null") + " | "
                + ((operators[2] != null) ? operators[2].toString() : "null");
    }
}
