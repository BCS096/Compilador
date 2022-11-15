package sintactic.nodes;

import sintactic.LogicOpType;

public class LogicOpNode extends BaseNode {

    LogicOpType type;

    public LogicOpNode(LogicOpType type, int line, int column) {
        super("LOGIC_OP", false, line, column);
        this.type = type;
    }

    public LogicOpType getType(){
        return type;
    }
}
