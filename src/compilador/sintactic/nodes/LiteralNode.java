package sintactic.nodes;

import sintactic.TypeEnum;

public class LiteralNode extends BaseNode{
    
    private final String literal;
    private final TypeEnum type;
    
    public LiteralNode(String literal, TypeEnum type, int line, int column){
        super("LITERAL", true, line, column);
        this.literal = literal;
        this.type = type;
    }
    
    public TypeEnum getType() {
        return type;
    }
    
    public String getLiteral() {
        return literal;
    }
}
