package compilador.sintactic.nodes;

public class ArrayDeclNode extends BaseNode {

    private InitArrayNode initArray;

    public ArrayDeclNode() {
        super("ARRAY_DECL", true);
    }

    public ArrayDeclNode(InitArrayNode initArray, int line, int column) {
        super("ARRAY_DECL", false, line, column);
        this.initArray = initArray;
    }

    public InitArrayNode getInitArrayNode() {
        return initArray;
    }
}
