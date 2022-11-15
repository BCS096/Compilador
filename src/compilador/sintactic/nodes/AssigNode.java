package sintactic.nodes;

public class AssigNode extends BaseNode {

    private InitArrayNode initArray;
    private ExpressionNode expression1, expression2;
    private IdentifierNode identifier;

    public AssigNode(InitArrayNode initArray, ExpressionNode expression1, ExpressionNode expression2, IdentifierNode identifier, int line, int column) {
        super("ASSIG", false, line, column);
        this.initArray = initArray;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.identifier = identifier;
    }

    public InitArrayNode getInitArray() {
        return initArray;
    }

    public ExpressionNode getExpression1() {
        return expression1;
    }

    public ExpressionNode getExpression2() {
        return expression2;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

}
