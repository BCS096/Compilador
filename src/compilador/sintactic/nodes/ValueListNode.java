package sintactic.nodes;

import sintactic.TypeEnum;

public class ValueListNode extends BaseNode {

    private ExpressionNode exp;
    private ValueListNode valueList;

    public ValueListNode(ExpressionNode exp, ValueListNode valueList, int line, int column) {
        super("VALUE_LIST", false, line, column);
        this.exp = exp;
        this.valueList = valueList;
    }

    public ExpressionNode getExp() {
        return exp;
    }

    public ValueListNode getValueList() {
        return valueList;
    }

    public int getSize() {
        if (valueList == null) {
            return 1;
        } else {
            return valueList.getSize() + 1;
        }
    }

    public TypeEnum getType() {
        if (valueList == null) {
            return exp.getType();
        }

        if (exp.getType() == valueList.getType()) {
            return exp.getType();
        } else {
            return TypeEnum.NULL;
        }
    }
}
