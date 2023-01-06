
package tablas;

import tablas.IdDescripcion.TipoDescripcion;
import types.TypeEnum;

/**
 *
 * @author Bartomeu
 */
public class TupelDescripcion extends IdDescripcion{
    private final int variableNumber;
    private final TypeEnum type;
    private final boolean isVar;
    private int size; //al ser diferentes tipos como se haria
    private boolean init;
    
    public TupelDescripcion(int variableNumber, TypeEnum type, boolean isVar, boolean init) {
        super(TipoDescripcion.dtupel);
        this.variableNumber = variableNumber;
        this.type = type;
        this.isVar = isVar;
        this.size = -1;
        this.init = init;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public TypeEnum getType() {
        return type;
    }
    
    public boolean isVar() {
        return isVar;
    }

    public int getSize() {
        return size;
    }

    public boolean isInit() {
        return init;
    }
    
    public void setInit(Boolean init) {
        this.init = init;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
}
