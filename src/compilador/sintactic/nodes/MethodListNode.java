package compilador.sintactic.nodes;

public class MethodListNode extends BaseNode {

    private MethodNode method;
    private MethodListNode methodList;

    public MethodListNode() {
        super("METHOD_LIST", true);
    }

    public MethodListNode(MethodNode method, MethodListNode methodList, int line, int column) {
        super("METHOD_LIST", false, line, column);
        this.method = method;
        this.methodList = methodList;
    }

    public MethodNode getMethod() {
        return method;
    }

    public MethodListNode getMethodList() {
        return methodList;
    }

}
