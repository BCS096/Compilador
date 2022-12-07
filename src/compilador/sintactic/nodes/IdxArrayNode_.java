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

    private IdxArrayNode idxArray;
    private ExpressionNode exp;

    public IdxArrayNode_(IdxArrayNode idxArray, ExpressionNode exp, int line, int column) {
        super("IDX_ARRAY_", false, line, column);
        this.idxArray = idxArray;
        this.exp = exp;

    }

    public IdxArrayNode_() {
        super("IDX_ARRAY_", true);
    }

    public IdxArrayNode getIdxArray_() {
        return idxArray;
    }

    public ExpressionNode getExp() {
        return exp;
    }
}
