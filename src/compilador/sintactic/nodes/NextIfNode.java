package compilador.sintactic.nodes;

public class NextIfNode extends BaseNode {

    private SentenceListNode sentenceList;
    private ExpressionNode exp;
    private NextIfNode nextIf;

    public NextIfNode() {
        super("NEXT_IF", true);
    }

    public NextIfNode(SentenceListNode sentenceList, ExpressionNode exp, NextIfNode nextIf, int line, int column) {
        super("NEXT_IF", false, line, column);
        this.sentenceList = sentenceList;
        this.exp = exp;
        this.nextIf = nextIf;
    }

    public SentenceListNode getSentenceList() {
        return sentenceList;
    }

    public ExpressionNode getExpression() {
        return exp;
    }

    public NextIfNode getNextIf() {
        return nextIf;
    }
}
