package tablas;

public class StringDescripcion extends IdDescripcion{
    
    private final int variableNumber;
    private final int size = 128;
    private final boolean isVar;
    private boolean init;
    
    public StringDescripcion(int variableNumber, boolean isVar, boolean init) {
        super(TipoDescripcion.dstring);
        this.variableNumber = variableNumber;
        this.isVar = isVar;
        this.init = init;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public int getSize() {
        return size;
    }
    
    public boolean isVar() {
        return isVar;
    }

    public boolean isInit() {
        return init;
    }
    
    public void setInit(Boolean value) {
        init = value;
    }

    @Override
    public String toString() {
        return "StringDescription{" + "variableNumber=" + variableNumber + ", size=" + size + ", isVar=" + isVar + '}';
    }
}
