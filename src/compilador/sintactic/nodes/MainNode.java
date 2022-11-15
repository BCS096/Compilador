package sintactic.nodes;

public class MainNode extends BaseNode {

    private final SentenceListNode sentenceList;

    public MainNode(SentenceListNode sentenceList, int line, int column) {
        super("MAIN", false, line, column);
        this.sentenceList = sentenceList;
    }

    public SentenceListNode getSentenceList() {
        return sentenceList;
    }
}
