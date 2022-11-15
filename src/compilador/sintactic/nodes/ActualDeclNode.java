package sintactic.nodes;

public class ActualDeclNode extends BaseNode {

    private DeclElemNode declElem;
    private DeclArrayNode declArray;

    public ActualDeclNode(DeclElemNode declElem, DeclArrayNode declArray, int line, int column) {
        super("ACTUAL_DECL", false, line, column);
        this.declElem = declElem;
        this.declArray = declArray;
    }

    public DeclElemNode getDeclElem() {
        return this.declElem;
    }

    public DeclArrayNode getDeclArray() {
        return this.declArray;
    }

}
