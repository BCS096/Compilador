package types;

public enum LogicOpType {
    OR("or"),
    AND("and");
    
    private final String type;
    
    LogicOpType(String type) {
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
}
