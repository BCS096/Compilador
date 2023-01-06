package compilador.sintactic.nodes;

public class ProcNode extends BaseNode {

    private ParamListNode paramList;
    private SentenceListNode sentenceList;
    private IdentifierNode identifier;

    public ProcNode(ParamListNode paramList, SentenceListNode sentenceList, IdentifierNode identifier, int line, int column) {
        super("PROC", false, line, column);
        this.paramList = paramList;
        this.sentenceList = sentenceList;
        this.identifier = identifier;
    }

    public ParamListNode getParamList() {
        return paramList;
    }

    public SentenceListNode getSentenceList() {
        return sentenceList;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

}
