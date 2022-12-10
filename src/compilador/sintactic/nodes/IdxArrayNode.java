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
    private RecurseArrayNode recurseArray;
    private SimpleValueNode sv;
    
    public IdxArrayNode (RecurseArrayNode recurseArray, SimpleValueNode sv, int line, int column){
        super("IDX_ARRAY", false, line, column);
        this.recurseArray = recurseArray;
        this.sv = sv;        
    }

    public RecurseArrayNode getRecurseArray() {
        return recurseArray;
    }

    public SimpleValueNode getSv(){
        return sv;
    }
}
