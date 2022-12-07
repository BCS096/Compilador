package tablas;

public class TypeDescripcion extends IdDescripcion {

    private final TSB tsb;
    private final int lowLimit;
    private final int superiorLimit;

    public enum TSB {
        tsb_char, tsb_int, tsb_boolean
    }

    public TypeDescripcion(TSB tsb, int lowLimit, int superiorLimit) {
        super(TipoDescripcion.dtipus);
        this.tsb = tsb;
        this.lowLimit = lowLimit;
        this.superiorLimit = superiorLimit;
    }

    public TSB getTsb() {
        return tsb;
    }

    public int getLowLimit() {
        return lowLimit;
    }

    public int getSuperiorLimit() {
        return superiorLimit;
    }

    @Override
    public String toString() {
        return "TypeDescription{" + "tsb=" + tsb + ", lowLimit=" + lowLimit + ", superiorLimit=" + superiorLimit + '}';
    }
}
