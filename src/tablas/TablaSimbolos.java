/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 *
 * @author Bartomeu
 */
public class TablaSimbolos {
    private int n;
    private TablaAmbitos ta;
    private TablaExpansion te;
    private TablaDescripcion td;
    
    public TablaSimbolos(){
        n = 0;
        ta = new TablaAmbitos();
        te = new TablaExpansion();
        td = new TablaDescripcion();
        ta.set(n, 0);
        n++;
        ta.set(n, 0);
    }
    
    public void vaciar(){
        n = 0;
        ta = new TablaAmbitos();
        te = new TablaExpansion();
        td = new TablaDescripcion();
        ta.set(n, 0);
        n++;
        ta.set(n, 0);   
    }
    
    public void poner(String id, IdDescripcion desc){
        Data d = td.get(id);
        if(d.getNp() == n){
            //lanzar excepcion
            //variable ya declarada en este ambito
        }
        if(d != null){
            
        }
    }
}
