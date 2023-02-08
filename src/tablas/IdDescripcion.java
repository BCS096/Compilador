package tablas;

public class IdDescripcion {

    private final TipoDescripcion desc;

    public enum TipoDescripcion {
        dnula, dvar, dconst, dtipus, dproc, dfunc, dindex, dargin, darg, darray, dstring, dtupel, dcampo
    }

    public IdDescripcion(TipoDescripcion desc) {
        this.desc = desc;
    }

    public TipoDescripcion getTipoDescripcion() {
        return desc;
    }
}
