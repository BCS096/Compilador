package types;

public enum SentenceType {
    IF("if"),
    FOR("for"),
    WHILE("while"),
    DECL("decl"),
    INST("inst"),
    REPEAT("repeat until"),
    NONE("none");
    
    private final String type;
    
    SentenceType(String type) {
        this.type = type;
    }
    
    public String getSentenceType() {
        return this.type;
    }
}
