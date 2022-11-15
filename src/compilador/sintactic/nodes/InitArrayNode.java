package sintactic.nodes;

public class InitArrayNode extends BaseNode {

    private TypeIdNode typeId;
    private ExpressionNode exp;
    private ValueListNode valueList;

    public InitArrayNode(TypeIdNode typeId, ExpressionNode exp, ValueListNode valueList, int line, int column) {
        super("INIT_ARRAY", false, line, column);
        this.typeId = typeId;
        this.exp = exp;
        this.valueList = valueList;
    }

    public TypeIdNode getTypeId() {
        return typeId;
    }

    public ExpressionNode getExpression() {
        return exp;
    }

    public ValueListNode getValueList() {
        return valueList;
    }

}
