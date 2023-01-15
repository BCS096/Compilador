package compilador.sintactic.nodes;

public class AssigNode extends BaseNode {

    private InitArrayNode initArray;
    private InitTupelNode initTupel;
    private ExpressionNode expression;
    private GestIdxNode gidx;

    public AssigNode(InitArrayNode initArray,InitTupelNode initTupel, ExpressionNode expression, GestIdxNode gidx, int line, int column) {
        super("ASSIG", false, line, column);
        this.initArray = initArray;
        this.initTupel = initTupel;
        this.expression = expression;
        this.gidx = gidx;
    }

    public InitTupelNode getInitTupel() {
        return initTupel;
    }

    public InitArrayNode getInitArray() {
        return initArray;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

 

    public GestIdxNode getGestIdx() {
        return gidx;
    }

}
