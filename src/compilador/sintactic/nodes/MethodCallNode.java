package sintactic.nodes;

public class MethodCallNode extends BaseNode {

    private ParamInNode paramIn;
    private IdentifierNode identifier;

    public MethodCallNode(ParamInNode paramIn, IdentifierNode identifier, int line, int column) {
        super("METHOD_CALL", false, line, column);
        this.paramIn = paramIn;
        this.identifier = identifier;
    }

    public ParamInNode getParamIn() {
        return paramIn;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

}
