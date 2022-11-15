package sintactic.nodes;

public class DeclArrayNode extends BaseNode {

    private TypeIdNode typeId;
    private ArrayDeclNode arrayDecl;
    private IdentifierNode identifier;

    public DeclArrayNode(TypeIdNode typeId, ArrayDeclNode arrayDecl, IdentifierNode identifier, int line, int column) {
        super("DECL_ARRAY", false, line, column);
        this.typeId = typeId;
        this.arrayDecl = arrayDecl;
        this.identifier = identifier;
    }

    public TypeIdNode getTypeId() {
        return this.typeId;
    }

    public ArrayDeclNode getArrayDecl() {
        return this.arrayDecl;
    }

    public IdentifierNode getIdentifier() {
        return this.identifier;
    }

}
