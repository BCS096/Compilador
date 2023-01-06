package types;

public enum SpecialOpType {
    INCREMENT("++"),
    DECREMENT("--");
    
    private final String type;
    
    SpecialOpType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
}
