package tablas;

import types.TypeEnum;

public class VarDescripcion extends IdDescripcion {
    
    private final int variableNumber;
    private final TypeEnum type;
    
    public VarDescripcion(int variableNumber, TypeEnum type) {
        super(TipoDescripcion.dvar);
        this.variableNumber = variableNumber;
        this.type = type;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public TypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return "VarDescription{" + "variableNumber=" + variableNumber + ", type=" + type + '}';
    }
    
}
