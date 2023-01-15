/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintactic.nodes;
import java_cup.runtime.ComplexSymbolFactory.Location;
import types.TypeEnum;

public class SpecialParamNode extends BaseNode {
    
    private final TypeEnum tsb;
    private final TypeIdNode type;

    public SpecialParamNode(TypeEnum tsb, TypeIdNode type, int line, int column) {
        super("SPECIAL_PARAM", false, line, column);
        this.type = type;
        this.tsb = tsb;
    }

    public Location getXleft() {
        return xleft;
    }

    public Location getXright() {
        return xright;
    }
    
    public TypeEnum getTSB() {
        return tsb;
    }
    
    public TypeIdNode getTypeId(){
        return type;
    }
}
