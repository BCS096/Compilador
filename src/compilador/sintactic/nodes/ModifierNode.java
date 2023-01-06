package compilador.sintactic.nodes;

import tablas.IdDescripcion.TipoDescripcion;


public class ModifierNode extends BaseNode {

    private final TipoDescripcion description; //const o variable
    
    public ModifierNode(TipoDescripcion description, Boolean empty, int line, int column) {
        super("MODIFIER", empty, line, column);
        this.description = description;
    }
    
    public TipoDescripcion getDescriptionType() {
        return this.description;
    }
}
