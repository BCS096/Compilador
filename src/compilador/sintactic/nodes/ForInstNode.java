package compilador.sintactic.nodes;

public class ForInstNode extends BaseNode {

    private SpecialOpNode specialOp;
    private ExpressionNode exp;
    private IdentifierNode identifier;

    public ForInstNode(SpecialOpNode specialOp, ExpressionNode exp, IdentifierNode identifier, int line, int column) {
        super("FOR_INST", false, line, column);
        this.specialOp = specialOp;
        this.exp = exp;
        this.identifier = identifier;
    }

    public SpecialOpNode getSpecialOp() {
        return specialOp;
    }

    public ExpressionNode getExpression() {
        return exp;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

}
