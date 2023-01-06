package compilador.sintactic.nodes;

public class DeclListNode extends BaseNode {

    private DeclListNode declList;
    private DeclNode decl;

    public DeclListNode() {
        super("DECL_LIST", true);
    }

    public DeclListNode(DeclListNode declList, DeclNode decl, int line, int column) {
        super("DECL_LIST", false, line, column);
        this.declList = declList;
        this.decl = decl;
    }

    public DeclListNode getDeclList() {
        return this.declList;
    }

    public DeclNode getDecl() {
        return this.decl;
    }

}
