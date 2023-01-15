package sintactic.semantic;

public enum InstructionType{
    CLONE("clone"), 
    ADD("add"), 
    SUB("sub"), 
    MUL("mul"), 
    DIV("div"),
    MOD("mod"), 
    NEG("minusValue"),
    AND("and"), 
    OR("or"), 
    NOT("not"),
    INDVALUE("indexed_value"), 
    ASSINDEX("indexed_assig"), 
    SKIP("skip"),
    GOTO("goto"),
    IFLT("iflt"),
    IFLE("ifle"),
    IFGT("ifgt"),
    IFGE("ifge"),
    IFEQ("ifeq"),
    IFNE("ifne"),
    INIT("preamble"),
    CALL("call"), 
    RETURN("return"),
    SIMPLEPARAM("simpleParam"), 
    COMPLEXPARAM("complexParam"),
    READ("read"),
    PRINT("print"),
    PRINTLN("println");
    
    String type;
    
    InstructionType(String type){
        this.type = type;
    }
    
    String getInstructionCode() {
        return type;
    }
}