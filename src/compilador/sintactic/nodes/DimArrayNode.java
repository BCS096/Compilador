/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

/**
 *
 * @author tomeu
 */
public class DimArrayNode extends BaseNode {
    
    DimArrayNode nextDim;
    ExpressionNode dim;
    
    public DimArrayNode (DimArrayNode nextDim, ExpressionNode dim, int line, int column){
        super("DIM_ARRAY", false, line, column);
        this.nextDim = nextDim;
        this.dim = dim;
    }

    public DimArrayNode getNextDim() {
        return nextDim;
    }

    public ExpressionNode getDim() {
        return dim;
    }
    
    
}
