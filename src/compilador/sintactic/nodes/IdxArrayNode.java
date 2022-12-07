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
    private IdxArrayNode_ idxArray_;
    private ExpressionNode exp;
    private SimpleValueNode sv;
    
    public IdxArrayNode (IdxArrayNode_ idxArray_, ExpressionNode exp, SimpleValueNode sv, int line, int column){
        super("IDX_ARRAY", false, line, column);
        this.idxArray_ = idxArray_;
        this.exp = exp;
        this.sv = sv;        
    }

    public IdxArrayNode_ getIdxArray_() {
        return idxArray_;
    }

    public ExpressionNode getExp() {
        return exp;
    }
    
    public SimpleValueNode getSv(){
        return sv;
    }
}
