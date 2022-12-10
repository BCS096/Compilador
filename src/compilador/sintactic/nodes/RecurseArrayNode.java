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
public class RecurseArrayNode extends BaseNode {

    private ArrayRecurseNode arrayRecurse;
    private ExpressionNode exp;

    public RecurseArrayNode(ArrayRecurseNode arrayRecurse, ExpressionNode exp, int line, int column) {
        super("RECURSE_ARRAY", false, line, column);
        this.arrayRecurse = arrayRecurse;
        this.exp = exp;

    }

    public ArrayRecurseNode getArrayRecurse() {
        return arrayRecurse;
    }

    public ExpressionNode getExp() {
        return exp;
    }
}
