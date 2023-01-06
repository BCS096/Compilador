package compilador.sintactic.nodes;

public class InitArrayNode extends BaseNode {

    private TypeIdNode typeId;
    private DimArrayNode dim;

    public InitArrayNode(TypeIdNode typeId, DimArrayNode dim, int line, int column) {
        super("INIT_ARRAY", false, line, column);
        this.typeId = typeId;
        this.dim = dim;
    }

    public TypeIdNode getTypeId() {
        return typeId;
    }

    public DimArrayNode getDimArray(){
        return dim;
    }

}
