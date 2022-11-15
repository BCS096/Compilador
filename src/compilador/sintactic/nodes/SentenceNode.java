package compilador.sintactic.nodes;

import sintactic.SentenceType;

public class SentenceNode extends BaseNode {

    private DeclNode decl;
    private InstNode inst;
    private ExpressionNode exp;
    private SentenceListNode sentenceList;
    private NextIfNode nextIf;
    private ForInstNode forInst;

    private final SentenceType sentenceType;

    public SentenceNode(DeclNode decl, InstNode inst, ExpressionNode exp,
            SentenceListNode sentenceList, NextIfNode nextIf,
            ForInstNode forInst, SentenceType sentenceType, int line, int column) {
        super("SENTENCE", false, line, column);
        this.decl = decl;
        this.inst = inst;
        this.exp = exp;
        this.sentenceList = sentenceList;
        this.nextIf = nextIf;
        this.forInst = forInst;
        this.sentenceType = sentenceType;
    }

    public DeclNode getDecl() {
        return decl;
    }

    public InstNode getInst() {
        return inst;
    }

    public ExpressionNode getExpression() {
        return exp;
    }

    public SentenceListNode getSentenceList() {
        return sentenceList;
    }

    public NextIfNode getNextIf() {
        return nextIf;
    }

    public ForInstNode getForInst() {
        return forInst;
    }

    public SentenceType getSentenceType() {
        return sentenceType;
    }
}
