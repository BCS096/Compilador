package tablas;

import java.util.ArrayList;

/**
 *
 * @author emanu
 */
public class TablaVariables {
    private final ArrayList<Variable> tv;
    private int numeroVariable;

    public TablaVariables() {
        this.tv = new ArrayList<>();
        this.numeroVariable = 0;
    }

    /**
     * Get a variable
     * @param index
     * @return
     */
    public Variable get(int index) {
        return tv.get(index);
    }

    /**
     * Put a new variable
     * @param variableData
     * @return variableData Returns the new variable
     */
    public Variable put(Variable variable) {
        tv.add(variable);
        numeroVariable++;
        return variable;
    }

    public int getContador() {
        return numeroVariable;
    }

    public void decrement() {
        numeroVariable--;
    }
}
