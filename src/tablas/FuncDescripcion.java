package tablas;

import types.TypeEnum;

public class FuncDescripcion extends IdDescripcion {
    private final int procedureNumber;
    private TypeEnum type;
    
    public FuncDescripcion(int procedureNumber, TypeEnum type) {
        super(TipoDescripcion.dfunc);
        this.procedureNumber = procedureNumber;
        this.type = type;
    }

    public int getProcedureNumber() {
        return procedureNumber;
    }
    
    public TypeEnum getType(){
        return type;
    }

    @Override
    public String toString() {
        return "FuncDescription{" + "procedureNumber=" + procedureNumber + ", type=" + type + '}';
    }
}

