
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
    private int size;
    private boolean init;
    
    public TupelDescripcion(int variableNumber, TypeEnum type, boolean init) {
        super(TipoDescripcion.dtupel);
        this.variableNumber = variableNumber;
        this.type = type;
        this.size = -1;
        this.init = init;
    }

    public int getVariableNumber() {
        return variableNumber;
    }

    public TypeEnum getType() {
        return type;
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
