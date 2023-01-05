/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author emanu
 */
public class TablaProcedimientos {

    private final HashMap<String, Procedimiento> tp;
    private int numProcedimientos;

    public TablaProcedimientos() {
        this.tp = new HashMap<>();
        this.numProcedimientos = 0;
    }

    /**
     * Dato de un procedimiento
     *
     * @param id
     * @return Procedimiento
     */
    public Procedimiento get(String id) {
        return tp.get(id);
    }
    
    public Set<String> getKeys(){
        return this.tp.keySet();
    }

    /**
     * Poner una nueva variable
     *
     * @param id
     * @param Procedimiento
     * @return true si ha sido insertado, false si no
     */
    public boolean put(String id, Procedimiento proc) {
        if (tp.containsKey(id)) {
            return false;
        }

        tp.put(id, proc);
        this.numProcedimientos++;
        return true;
    }

    public int getContador() {
        return numProcedimientos;
    }

    public void decrementCounter() {
        this.numProcedimientos--;
    }

}
