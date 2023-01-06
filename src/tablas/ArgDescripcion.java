package tablas;

import types.TypeEnum;

public class ArgDescripcion extends IdDescripcion {
    
    private final TypeEnum type;
    private final String name;

    public ArgDescripcion(TypeEnum type, String name) {
        super(TipoDescripcion.darg);
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
        return "ArgDescription{" + "type=" + type + ", name=" + name + '}';
    }
    
}
