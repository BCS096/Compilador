package sintactic.nodes;

public class DeclElemNode extends BaseNode {

    private TypeIdNode typeId;
    private ElemListNode elemList;

    public DeclElemNode(TypeIdNode typeId, ElemListNode elemList, int line, int column) {
        super("DECL_ELEM", false, line, column);
        this.typeId = typeId;
        this.elemList = elemList;
    }

    public TypeIdNode getTypeId() {
        return this.typeId;
    }

    public ElemListNode getElemList() {
        return this.elemList;
    }

}
