/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.util.ArrayList;

/**
 *
 * @author Bartomeu
 */
public class TablaExpansion {

    private ArrayList<Data> expansion = new ArrayList<>();

    public TablaExpansion() {
    }

    public void put(int idxe, Data d) {
        if (expansion.contains(d)) {
            expansion.set(idxe, d);
        }
        this.expansion.add(idxe, d);
    }

    public Data get(int i) {
        return this.expansion.get(i);
    }
}
