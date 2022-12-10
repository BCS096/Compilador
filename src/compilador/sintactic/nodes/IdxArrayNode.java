/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

/**
 *
 * @author tomeu
 */
public class IdxArrayNode extends BaseNode {
    private ExpressionNode exp;
    private IdxArrayNode idxArray;
    
    public IdxArrayNode (ExpressionNode exp, IdxArrayNode idxArray, int line, int column){
        super("IDX_ARRAY", false, line, column);
        this.idxArray = idxArray;
        this.exp = exp;
    }
    
    public IdxArrayNode (){
        super("IDX_ARRAY", true);
    }

    public ExpressionNode getExp(){
        return exp;
    }
    
    public IdxArrayNode getIdxArray() {
        return idxArray;
    }
}
