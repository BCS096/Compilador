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
    private IdxArrayNode idxArray;
    private ExpressionNode exp;
    private SimpleValueNode sv;
    
    public IdxArrayNode (IdxArrayNode idxArray, ExpressionNode exp, SimpleValueNode sv, int line, int column){
        super("IDX_ARRAY", false, line, column);
        this.idxArray = idxArray;
        this.exp = exp;
        this.sv = sv;
        
    }

    public IdxArrayNode getIdxArray() {
        return idxArray;
    }

    public ExpressionNode getExp() {
        return exp;
    }

    public SimpleValueNode getSv() {
        return sv;
    }
    
}
