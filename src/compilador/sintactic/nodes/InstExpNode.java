package compilador.sintactic.nodes;

import types.TypeEnum;


public class InstExpNode extends BaseNode {

    private SpecialOpNode specialOp;
    private MethodCallNode methodCall;
    private IdentifierNode identifier;
    private Boolean isReadMethod;

    private TypeEnum type;

    public InstExpNode(SpecialOpNode specialOp, MethodCallNode methodCall, IdentifierNode identifier, Boolean isReadMethod, int line, int column) {
        super("INST_EXP", false, line, column);
        this.type = TypeEnum.NULL;
        this.specialOp = specialOp;
        this.methodCall = methodCall;
        this.identifier = identifier;
        this.isReadMethod = isReadMethod;
    }

    public SpecialOpNode getSpecialOp() {
        return specialOp;
    }

    public MethodCallNode getMethodCall() {
        return methodCall;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public Boolean isReadMethod() {
        return isReadMethod;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }
}
