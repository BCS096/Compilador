package tablas;

import types.TypeEnum;

public class CampoDescripcion extends IdDescripcion {
    
    private final TypeEnum type;
    private final String name;
    // si Campo es una tupla o array el name es el nombre real de la tupla para ir a buscarla en la tabla de simbolos

    public CampoDescripcion(TypeEnum type, String name) {
        super(TipoDescripcion.dcampo);
        this.type = type;
        this.name = name;
    }

    public TypeEnum getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CampoDescripcion" + "type=" + type + ", name=" + name + '}';
    }
    
}
