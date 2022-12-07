package compilador.sintactic.nodes;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class BaseNode extends ComplexSymbol {

    private static int idAutoIncrement = 0;
    protected boolean empty;
    protected int reference;
    protected int line;
    protected int column;
    //CAMBIAR, NO DEFINITIVO
    public enum DescriptionType {
        dnula, dvar, dconst, dtipus, dproc, dfunc, dindex, dargin, darg, darray, dstring
    }

    /**
     * Constructor per crear una instància buida amb un nom, com a conseqüència
     * d'un error o una produció que deriva lambda.
     *
     * @param variable
     * @param empty
     */
    public BaseNode(String variable, Boolean empty) {
        super(variable, idAutoIncrement++);
        this.empty = empty;
    }

    /**
     * Constructor per crear una instància buida amb un nom, com a conseqüència
     * d'un error o una produció que deriva lambda.
     *
     * @param variable
     * @param empty
     * @param line
     * @param column
     */
    public BaseNode(String variable, Boolean empty, int line, int column) {
        super(variable, idAutoIncrement++);
        this.empty = empty;
        this.line = line;
        this.column = column;
    }

    /**
     * Mètode que permet determinar si la variable és buida (lambda) o bé perquè
     * hi ha un error semàntic.
     *
     * @return true si és lambda/error false altrement
     */
    public boolean isEmpty() {
        return empty;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }
}
