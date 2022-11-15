package sintactic.nodes;

public class ElemIdAssigNode extends BaseNode {

    private ExpressionNode exp;
    private IdentifierNode identifier;

    public ElemIdAssigNode(ExpressionNode exp, IdentifierNode identifier, int line, int column) {
        super("ELEM_ID_ASSIG", false, line, column);
        this.exp = exp;
        this.identifier = identifier;
    }

    public ExpressionNode getExp() {
        return this.exp;
    }

    public IdentifierNode getIdentifier() {
        return this.identifier;
    }

}
