package tablas;

import types.TypeEnum;

public class ConstDescripcion extends IdDescripcion {
    
    private final int variableNumber;
    private final TypeEnum type;
    private boolean init;
    
    public ConstDescripcion(int variableNumber, TypeEnum type, boolean init) {
        super(TipoDescripcion.dconst);
        this.variableNumber = variableNumber;
        this.type = type;
        this.init = init;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public TypeEnum getType() {
        return type;
    }
    
    public boolean isInit() {
        return init;
    }
    
    public void setInit(boolean value){
        this.init = value;
    }

    @Override
    public String toString() {
        return "Const description{" + "variableNumber=" + variableNumber + ", type=" + type + ", init= "+this.init+'}';
    }
}
