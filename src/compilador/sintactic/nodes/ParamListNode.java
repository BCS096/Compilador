package sintactic.nodes;

public class ParamListNode extends BaseNode {

    private ActualParamListNode actualParamList;

    public ParamListNode() {
        super("PARAM_LIST", true);
    }

    public ParamListNode(ActualParamListNode actualParamList, int line, int column) {
        super("PARAM_LIST", false, line, column);
        this.actualParamList = actualParamList;
    }

    public ActualParamListNode getActualParamList() {
        return actualParamList;
    }

}
