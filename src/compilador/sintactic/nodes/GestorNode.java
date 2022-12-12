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
    private ExpressionNode exp;
    private GestorNode gest;
    private GestIdxNode gestIdx;
    
    public GestorNode (ExpressionNode exp, GestorNode idxArray, GestIdxNode gestIdx, int line, int column){
        super("IDX_ARRAY", false, line, column);
        this.gest = idxArray;
        this.gestIdx = gestIdx;
        this.exp = exp;
    }

    public GestIdxNode getGestIdx() {
        return gestIdx;
    }
    
    public GestorNode (){
        super("GESTOR", true);
    }

    public ExpressionNode getExp(){
        return exp;
    }
    
    public GestorNode getGestor() {
        return gest;
    }
}
