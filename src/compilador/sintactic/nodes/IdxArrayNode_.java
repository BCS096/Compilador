/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.nodes;

/**
 *
 * @author emanu
 */
public class IdxArrayNode_ extends BaseNode {

    private IdxArrayNode_ idxArray_;
    private ExpressionNode exp;

    public IdxArrayNode_(IdxArrayNode_ idxArray_, ExpressionNode exp, int line, int column) {
        super("IDX_ARRAY_", false, line, column);
        this.idxArray_ = idxArray_;
        this.exp = exp;

    }

    public IdxArrayNode_() {
        super("IDX_ARRAY_", true);
    }

    public IdxArrayNode_ getIdxArray_() {
        return idxArray_;
    }

    public ExpressionNode getExp() {
        return exp;
    }
}
