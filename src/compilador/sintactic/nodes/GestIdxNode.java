/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.nodes;

/**
 *
 * @author Bartomeu
 */
public class GestIdxNode extends BaseNode{
   
    private IdentifierNode id;
    private GestorNode gest;
    
    public GestIdxNode (IdentifierNode id, GestorNode gest, int line, int column){
        super("GEST_IDX", false, line, column);
        this.id = id;
        this.gest = gest;
    }

    public IdentifierNode getId() {
        return id;
    }

    public GestorNode getGest() {
        return gest;
    }
}
