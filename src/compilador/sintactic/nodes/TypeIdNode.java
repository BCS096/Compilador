package sintactic.nodes;

import java_cup.runtime.ComplexSymbolFactory.Location;
import sintactic.TypeEnum;

public class TypeIdNode extends BaseNode {
    
    private final TypeEnum type;

    public TypeIdNode(TypeEnum type, int line, int column) {
        super("TYPE_ID", false, line, column);
        this.type = type;
    }

    public Location getXleft() {
        return xleft;
    }

    public Location getXright() {
        return xright;
    }
    
    public TypeEnum getType() {
        return type;
    }
}
