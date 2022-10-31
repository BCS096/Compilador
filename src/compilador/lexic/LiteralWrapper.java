package compilador.lexic;

public class LiteralWrapper {

    final Object literal;
    final int line;
    final int column;

    public LiteralWrapper(Object literal, int line, int column) {
        this.literal = literal;
        this.line = line;
        this.column = column;
    }
    
    public Object getLiteral() {
        return literal;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
}
