package tablas;

public class ProcDescripcion extends IdDescripcion {
    
    private final int procedureNumber;
    
    public ProcDescripcion(int procedureNumber) {
        super(TipoDescripcion.dproc);
        this.procedureNumber = procedureNumber;
    }

    public int getProcedureNumber() {
        return procedureNumber;
    }

    @Override
    public String toString() {
        return "ProcDescription{" + "procedureNumber=" + procedureNumber + '}';
    }
    
    
    
}
