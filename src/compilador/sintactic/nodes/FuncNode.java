package compilador.sintactic.nodes;

public class FuncNode extends BaseNode {

    private TypeIdNode typeId;
    private ParamListNode paramList;
    private SentenceListNode sentenceList;
    private ExpressionNode exp;
    private IdentifierNode id;

    public FuncNode(IdentifierNode id, ParamListNode paramList, TypeIdNode typeId, SentenceListNode sentenceList, ExpressionNode exp, int line, int column) {
        super("FUNC", false, line, column);
        this.typeId = typeId;
        this.paramList = paramList;
        this.sentenceList = sentenceList;
        this.exp = exp;
        this.id = id;
    }

    public TypeIdNode getTypeId() {
        return typeId;
    }

    public ParamListNode getParamList() {
        return paramList;
    }

    public SentenceListNode getSentenceList() {
        return sentenceList;
    }

    public ExpressionNode getExp() {
        return exp;
    }

    public IdentifierNode getId() {
        return id;
    }

}
