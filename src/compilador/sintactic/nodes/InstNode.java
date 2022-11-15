package compilador.sintactic.nodes;

import sintactic.InstType;

public class InstNode extends BaseNode {

    private InstExpNode instExp;
    private AssigNode assig;
    private ExpressionNode printExpression;

    private InstType instType;

    public InstNode(InstExpNode instExp, AssigNode assig, ExpressionNode printExpression, InstType instType, int line, int column) {
        super("INST", false, line, column);
        this.instExp = instExp;
        this.assig = assig;
        this.printExpression = printExpression;
        this.instType = instType;
    }

    public InstExpNode getInstExp() {
        return instExp;
    }

    public AssigNode getAssig() {
        return assig;
    }

    public ExpressionNode getPrintExpression() {
        return printExpression;
    }

    public InstType getInstType() {
        return instType;
    }
}
