/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.util.HashMap;

/**
 *
 * @author Bartomeu
 */
public class TablaDescripcion {
    private HashMap <String, Data> tabla = new HashMap<>();
    
    public TablaDescripcion(){}
    
    public Data get(String id){
        return tabla.get(id);
    }

    boolean existe(String identifier) {
        return tabla.containsKey(identifier);
    }
}
