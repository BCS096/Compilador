package compilador.sintactic.nodes;

public class ParamInNode extends BaseNode {

    private ExpressionNode exp;
    private ParamInNode paramIn;

    public ParamInNode() {
        super("PARAM_IN", true);
    }

    public ParamInNode(ExpressionNode exp, ParamInNode paramIn, int line, int column) {
        super("PARAM_IN", false, line, column);
        this.exp = exp;
        this.paramIn = paramIn;
    }

    public ExpressionNode getExpression() {
        return exp;
    }

    public ParamInNode getParamIn() {
        return paramIn;
    }

}
