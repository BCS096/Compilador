package compilador.sintactic.nodes;

public class GestTupelNode extends BaseNode {

    private IdentifierNode identifier;

    public GestTupelNode(IdentifierNode identifier, int line, int column) {
        super("GEST_TUPEL", false, line, column);
        this.identifier = identifier;
    }
    
    public GestTupelNode(){
        super("GEST_TUPEL", true);
    }


    public IdentifierNode getIdentifier() {
        return this.identifier;
    }

}
