package sintactic.nodes;

public class DeclNode extends BaseNode {

    private ModifierNode modifier;
    private ActualDeclNode actualDecl;

    public DeclNode(ModifierNode modifier, ActualDeclNode actualDecl, int line, int column) {
        super("DECL", false, line, column);
        this.modifier = modifier;
        this.actualDecl = actualDecl;
    }

    public ModifierNode getModifier() {
        return this.modifier;
    }

    public ActualDeclNode getActualDecl() {
        return this.actualDecl;
    }

}
