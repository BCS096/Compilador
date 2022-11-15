package compilador.sintactic.nodes;

import sintactic.TypeEnum;

public class SimpleValueNode extends BaseNode {

    private IdentifierNode id;
    private LiteralNode literal;
    private IdxArrayNode idxArray
    private InstExpNode instExp;
    private SimpleValueNode simpl;
    private TypeEnum type;

    public SimpleValueNode(IdentifierNode id, LiteralNode literal,
            IdxArrayNode idxArray, InstExpNode instExp, SimpleValueNode simpl,
            TypeEnum type, int line, int column) {
        super("SIMPLE_VALUE", false, line, column);
        this.id = id;
        this.literal = literal;
        this.idxArray = idxArray;
        this.instExp = instExp;
        this.simpl = simpl;
        this.type = type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public IdxArrayNode getIdxArray() {
        return idxArray;
    }

    public IdentifierNode getId() {
        return id;
    }

    public LiteralNode getLiteral() {
        return literal;
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
