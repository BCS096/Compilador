package compilador.sintactic.nodes;

public class ParamNode extends BaseNode {

    private TypeIdNode typeId;
    private SpecialParamNode type;
    private IdentifierNode id;

    public ParamNode(TypeIdNode typeId,SpecialParamNode type, IdentifierNode id, int line, int column) {
        super("PARAM", false, line, column);
        this.typeId = typeId;
        this.type = type;
        this.id = id;
    }

    public TypeIdNode getTypeId() {
        return typeId;
    }

    public SpecialParamNode getSpecialParam() {
        return type;
    } 
    
    public IdentifierNode getId() {
        return id;
    }

}
