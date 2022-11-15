package sintactic.nodes;

public class BinaryOpNode extends BaseNode {

    private AritOpNode arit;
    private RelOpNode rel;
    private LogicOpNode logic;

    public BinaryOpNode(AritOpNode arit, RelOpNode rel, LogicOpNode logic, int line, int column) {
        super("BINARY_OP", false, line, column);
        this.arit = arit;
        this.rel = rel;
        this.logic = logic;
    }

    public AritOpNode getArit() {
        return arit;
    }

    public RelOpNode getRel() {
        return rel;
    }

    public LogicOpNode getLogic() {
        return logic;
    }

}
