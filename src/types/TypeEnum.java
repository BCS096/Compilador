package types;



public enum TypeEnum {
    INT("int"),
    CHAR("char"),
    BOOL("boolean"),
    STRING("string"),
    VOID("void"),
    NULL("null");

    private final String type;
    public static final int ERROR = -2;

    TypeEnum(String type) {
        this.type = type;
    }

    public String getTypeString() {
        return this.type;
    }

    public int getBytes() {
        switch (this) {
            case INT:
                return Integer.BYTES;
            case CHAR:
                return Character.BYTES;
            case BOOL:
                return 1;
            case STRING:
                return 128;
            case VOID:
                return -1;
            case NULL:
                return 0;
        }
        //Error = -2
        return ERROR;
    }
}
