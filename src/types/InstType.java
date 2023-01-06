package types;

public enum InstType {
    PRINT("print"),
    PRINTLN("println"),
    READ("read"),
    EXP("exp"),
    ASSIG("assig");
    
    private final String type;
    
    InstType(String type) {
        this.type = type;
    }
    
    public String getInstType() {
        return this.type;
    }
}
