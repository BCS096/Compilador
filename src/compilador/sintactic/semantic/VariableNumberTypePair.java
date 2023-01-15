package compilador.sintactic.semantic;

import types.TypeEnum;

public class VariableNumberTypePair {

    private final int variableNumber;
    private final TypeEnum type;

    public VariableNumberTypePair(int variableNumber, TypeEnum type) {
        this.variableNumber = variableNumber;
        this.type = type;
    }

    public int getVariableNumber() {
        return this.variableNumber;
    }

    public TypeEnum getType() {
        return this.type;
    }
}
