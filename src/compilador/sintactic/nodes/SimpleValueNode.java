package sintactic.nodes;

import sintactic.TypeEnum;

public class SimpleValueNode extends BaseNode {

    private IdentifierNode id;
    private LiteralNode literal;
    private ExpressionNode exp;
    private InstExpNode instExp;
    private SimpleValueNode simpl;
    private TypeEnum type;

    public SimpleValueNode(IdentifierNode id, LiteralNode literal,
            ExpressionNode exp, InstExpNode instExp, SimpleValueNode simpl,
            TypeEnum type, int line, int column) {
        super("SIMPLE_VALUE", false, line, column);
        this.id = id;
        this.literal = literal;
        this.exp = exp;
        this.instExp = instExp;
        this.simpl = simpl;
        this.type = type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public IdentifierNode getId() {
        return id;
    }

    public LiteralNode getLiteral() {
        return literal;
    }

    public ExpressionNode getExp() {
        return exp;
    }

    public InstExpNode getInstExp() {
        return instExp;
    }

    public SimpleValueNode getSimpl() {
        return simpl;
    }

    public TypeEnum getType() {
        return type;
    }
}
