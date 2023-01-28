package compilador.sintactic.nodes;

public class AssigNode extends BaseNode {

    private ExpressionNode expression;
    private GestIdxNode gidx;

    public AssigNode(ExpressionNode expression, GestIdxNode gidx, int line, int column) {
        super("ASSIG", false, line, column);
        this.expression = expression;
        this.gidx = gidx;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    public GestIdxNode getGestIdx() {
        return gidx;
    }

}
