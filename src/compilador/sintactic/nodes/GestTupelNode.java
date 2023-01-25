package compilador.sintactic.nodes;

public class GestTupelNode extends BaseNode {

    private IdentifierNode identifier;
    private GestTupelNode gestTupel;

    public GestTupelNode(IdentifierNode identifier, GestTupelNode gestTupel, int line, int column) {
        super("GEST_TUPEL", false, line, column);
        this.identifier = identifier;
        this.gestTupel = gestTupel;
    }
    
    public GestTupelNode(){
        super("GEST_TUPEL", true);
    }

    public GestTupelNode getTupel() {
        return this.gestTupel;
    }

    public IdentifierNode getIdentifier() {
        return this.identifier;
    }

}
