package tablas;

public class ArgInDescripcion extends IdDescripcion {

    private final int variableNumber;
    private final String type;

    public ArgInDescripcion(int variableNumber, String type) {
        super(TipoDescripcion.dargin);
        this.variableNumber = variableNumber;
        this.type = type;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ArgInDescription{" + "variableNumber=" + variableNumber + ", type=" + type + '}';
    }

}
