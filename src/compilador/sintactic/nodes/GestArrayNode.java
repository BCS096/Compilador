package compilador.sintactic.nodes;

public class GestArrayNode extends BaseNode {

    private ExpressionNode exp;
    private GestArrayNode gestArray;

    public GestArrayNode(ExpressionNode exp, GestArrayNode gestArray, int line, int column) {
        super("GEST_ARRAY", false, line, column);
        this.exp = exp;
        this.gestArray = gestArray;
    }
    
    public GestArrayNode(){
        super("GEST_ARRAY", true);
    }

    public ExpressionNode getExp() {
        return this.exp;
    }

    public GestArrayNode getGestArray() {
        return this.gestArray;
    }

}
