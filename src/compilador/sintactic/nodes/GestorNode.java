/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

/**
 *
 * @author tomeu
 */
public class GestorNode extends BaseNode {
    private GestArrayNode gestArray;
    private GestTupelNode gestTupel;
    
    public GestorNode (GestArrayNode gestArray, GestTupelNode gestTupel, int line, int column){
        super("IDX_ARRAY", false, line, column);
        this.gestArray = gestArray;
        this.gestTupel = gestTupel;
    }

    public GestArrayNode getGestArray() {
        return gestArray;
    }

    public GestTupelNode getGestTupel() {
        return gestTupel;
    }
}
