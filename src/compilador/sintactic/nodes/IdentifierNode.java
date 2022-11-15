package compilador.sintactic.nodes;

public class IdentifierNode extends BaseNode {

    private String identifierLiteral;
    
    public IdentifierNode(String literal, int line, int column) {
        super("ID", false, line, column);
        this.identifierLiteral = literal;
    }
    
    public String getIdentifierLiteral() {
        return identifierLiteral;
    }
}
