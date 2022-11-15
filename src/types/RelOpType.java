package types;

public enum RelOpType {
    EQ("=="),
    NEQ("!="),
    LT("<"),
    GT(">"),
    LE("<="),
    GE(">=");
    
    private final String type;
    
    RelOpType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
}
