package compilador.sintactic.nodes;


public class ModifierNode extends BaseNode {

    private final DescriptionType description; //const o variable
    
    public ModifierNode(DescriptionType description, Boolean empty, int line, int column) {
        super("MODIFIER", empty, line, column);
        this.description = description;
    }
    
    public DescriptionType getDescriptionType() {
        return this.description;
    }
}
