/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

import types.TypeEnum;

/**
 *
 * @author tomeu
 */
public class Desplazamiento {
    public int desp; // número de la variable que estarà en la tabla de variables que guarda el desplazamiento
    public String id;
    public TypeEnum type;
    
    public Desplazamiento(){}
    
    public Desplazamiento(int desp, String id, TypeEnum type){
        this.desp = desp;
        this.id = id;
        this.type = type;
    }
    
}
