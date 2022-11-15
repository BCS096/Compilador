package compilador.sintactic.nodes;

import sintactic.SpecialOpType;

public class SpecialOpNode extends BaseNode {

    SpecialOpType type;

    public SpecialOpNode(SpecialOpType type, int line, int column) {
        super("SPECIAL_OP", true, line, column);
        this.type = type;
    }

    public SpecialOpType getType(){
        return type;
    }
}
