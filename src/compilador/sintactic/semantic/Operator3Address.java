package compilador.sintactic.semantic;

public class Operator3Address {

    private String label = null;
    private Object castValue;
    private Type type;
    private int reference;

    public static enum CastType {
        INT, CHAR, BOOL, STRING;
    }
    private CastType castType;
    
    public Type getType(){
        return this.type;
    }
    
    public CastType getCastType(){
        return this.castType;
    }

    public Operator3Address(String label) {
        this.label = label;
    }

    public Operator3Address(Object castValue, CastType castType) {
        this.castValue = castValue;
        this.castType = castType;
        this.type = Type.literal;
    }

    Operator3Address(int reference) {
        this.type = Type.reference;
        this.reference = reference;
    }
    
    public Object getLiteral(){
        return this.castValue;
    }

    public int getReference() {
        return this.reference;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public enum Type {
        literal,
        reference;
    }

    @Override
    public String toString() {
        if (label == null) {
            if (this.type == Type.literal) {
                return "LiteralOp(value: " + castValue + ")";
            } else {
                return "ReferenceOp(ref: " + reference + ")";
            }
        } else {
            return "LabelOp(label: " + label + ")";
        }
    }
}
