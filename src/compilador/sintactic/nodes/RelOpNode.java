package sintactic.nodes;

import sintactic.RelOpType;

public class RelOpNode extends BaseNode {

    RelOpType type;

    public RelOpNode(RelOpType type, int line, int column) {
        super("REL_OP", false, line, column);
        this.type = type;
    }

    public RelOpType getType(){
        return type;
    }
}
