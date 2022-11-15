package sintactic.nodes;

import sintactic.AritOpType;

public class AritOpNode extends BaseNode {

    AritOpType type;

    public AritOpNode(AritOpType type, int line, int column) {
        super("ARIT_OP", false, line, column);
        this.type = type;
    }

    public AritOpType getType(){
        return type;
    }
}
