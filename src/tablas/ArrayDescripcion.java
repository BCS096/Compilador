package tablas;

import types.TypeEnum;

public class ArrayDescripcion extends IdDescripcion {
    
    private final int variableNumber;
    private final TypeEnum type;
    private int size; // numero de la variable que está en la tabla de variables que contiene el total de ocupacion de bytes de la array
    private boolean init;
    
    public ArrayDescripcion(int variableNumber, TypeEnum type, boolean init) {
        super(TipoDescripcion.darray);
        this.variableNumber = variableNumber;
        this.type = type;
        this.size = -1;
        this.init = init;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public TypeEnum getType() {
        return type;
    }
    
    public int getSize() {
        return size;
    }

    public boolean isInit() {
        return init;
    }
    
    public void setInit(Boolean init) {
        this.init = init;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isSizeSet() {
        return size != -1;
    }

    @Override
    public String toString() {
        return "ArrayDescription{" + "variableNumber=" + variableNumber + ", type=" + type + ", size=" + size + '}';
    }
}
