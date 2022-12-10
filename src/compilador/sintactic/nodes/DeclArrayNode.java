package compilador.sintactic.nodes;

public class DeclArrayNode extends BaseNode {

    private TypeIdNode typeId;
    private IdentifierNode identifier;
    private ArrayDeclNode arrayDecl;

    public DeclArrayNode(TypeIdNode typeId, IdentifierNode identifier, ArrayDeclNode arrayDecl,int line, int column) {
        super("DECL_ARRAY", false, line, column);
        this.typeId = typeId;
        this.identifier = identifier;
        this.arrayDecl = arrayDecl;
    }

    public TypeIdNode getTypeId() {
        return this.typeId;
    }
    
    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public ArrayDeclNode getArrayDecl() {
        return this.arrayDecl;
    }

}
