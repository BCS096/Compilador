package types;

public enum AritOpType {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    MOD("mod");
    
    private final String type;
    
    AritOpType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
}
