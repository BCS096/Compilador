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
public class TablaAmbitos {
    
    private ArrayList <Integer> ambitos = new ArrayList<>();
    
    public TablaAmbitos(){}
    
    public void nuevaEntrada(int n){
        ambitos.add(n, ambitos.get(n) + 1);
    }
    
    public void set(int n, int valor){
        ambitos.add(n, valor);
    }
   
    public int get(int n){ //ta[n]
        return ambitos.get(n);
    }
}
