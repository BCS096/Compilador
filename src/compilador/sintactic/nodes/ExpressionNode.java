package compilador.sintactic.nodes;

import sintactic.TypeEnum;

public class ExpressionNode extends BaseNode {

    private ExpressionNode exp1, exp2;
    private BinaryOpNode binOp;
    private SimpleValueNode simplVal;
    private NegOpNode negOp;
    private Mode mode;

    private String offset;

    public enum Mode {
        varMode, constMode, resultMode
    }
    private TypeEnum type;

    public ExpressionNode(ExpressionNode exp1, ExpressionNode exp2,
            BinaryOpNode binOp, SimpleValueNode simplVal,
            NegOpNode negOp, TypeEnum type, Mode mode, int line, int column) {
        super("EXP", false, line, column);

        this.exp1 = exp1;
        this.exp2 = exp2;
        this.binOp = binOp;
        this.simplVal = simplVal;
        this.negOp = negOp;
        this.type = type;
        this.mode = mode;
    }

    public ExpressionNode getExp1() {
        return exp1;
    }

    public ExpressionNode getExp2() {
        return exp2;
    }

    public BinaryOpNode getBinOp() {
        return binOp;
    }

    public SimpleValueNode getSimplVal() {
        return simplVal;
    }

    public NegOpNode getNegOp() {
        return negOp;
    }

    public Mode getMode() {
        return mode;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOffset() {
        return this.offset;
    }
}
