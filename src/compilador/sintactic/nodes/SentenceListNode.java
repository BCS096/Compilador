package compilador.sintactic.nodes;

public class SentenceListNode extends BaseNode {

    private SentenceNode sentence;
    private SentenceListNode sentenceList;

    public SentenceListNode() {
        super("SENTENCE_LIST", true);
    }

    public SentenceListNode(SentenceNode sentence, SentenceListNode sentenceList, int line, int column) {
        super("SENTENCE_LIST", false, line, column);
        this.sentence = sentence;
        this.sentenceList = sentenceList;
    }

    public SentenceNode getSentence() {
        return sentence;
    }

    public SentenceListNode getSentenceList() {
        return sentenceList;
    }

}
