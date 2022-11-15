package compilador.sintactic.nodes;

public class DeclArrayNode extends BaseNode {

    private TypeIdNode typeId;
    private ArrayDeclNode arrayDecl;

    public DeclArrayNode(TypeIdNode typeId, ArrayDeclNode arrayDecl,int line, int column) {
        super("DECL_ARRAY", false, line, column);
        this.typeId = typeId;
        this.arrayDecl = arrayDecl;
    }

    public TypeIdNode getTypeId() {
        return this.typeId;
    }

    public ArrayDeclNode getArrayDecl() {
        return this.arrayDecl;
    }

}
