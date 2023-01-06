package compilador.sintactic.nodes;

public class ActualDeclNode extends BaseNode {

    private DeclElemNode declElem;
    private DeclArrayNode declArray;
    private DeclTupelNode declTupel;

    public ActualDeclNode(DeclElemNode declElem, DeclArrayNode declArray, DeclTupelNode declTupel, int line, int column) {
        super("ACTUAL_DECL", false, line, column);
        this.declElem = declElem;
        this.declArray = declArray;
        this.declTupel = declTupel;
    }

    public DeclElemNode getDeclElem() {
        return this.declElem;
    }

    public DeclArrayNode getDeclArray() {
        return this.declArray;
    }
    
    public DeclTupelNode getDeclTupel() {
        return this.declTupel;
    }

}
