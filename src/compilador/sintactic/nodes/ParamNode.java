package sintactic.nodes;

public class ParamNode extends BaseNode {

    private TypeIdNode typeId;
    private IdentifierNode id;

    public ParamNode(TypeIdNode typeId, IdentifierNode id, int line, int column) {
        super("PARAM", false, line, column);
        this.typeId = typeId;
        this.id = id;
    }

    public TypeIdNode getTypeId() {
        return typeId;
    }

    public IdentifierNode getId() {
        return id;
    }

}
