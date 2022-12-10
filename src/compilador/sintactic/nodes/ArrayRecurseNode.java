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
public class ArrayRecurseNode extends BaseNode{
    
    private RecurseArrayNode recurseArray;
    
    public ArrayRecurseNode(RecurseArrayNode recurseArray, int line, int column){
        super("ARRAY_RECURSE", false, line, column);
        this.recurseArray = recurseArray;
    }
    
    public ArrayRecurseNode(){
        super("ARRAY_RECURSE", true);
    }

    public RecurseArrayNode getRecurseArray() {
        return recurseArray;
    }
    
}
