package sintactic.nodes;

public class ActualParamListNode extends BaseNode {

    private ParamNode param;
    private ActualParamListNode actualParamList;

    public ActualParamListNode(ParamNode param, ActualParamListNode actualParamList, int line, int column) {
        super("ACTUAL_PARAM_LIST", false, line, column);
        this.param = param;
        this.actualParamList = actualParamList;
    }

    public ParamNode getParam() {
        return param;
    }

    public ActualParamListNode getActualParamList() {
        return actualParamList;
    }
}
