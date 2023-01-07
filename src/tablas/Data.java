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
public class Data {
    
    private String id;
    private IdDescripcion descripcion;
    private int np; // en caso de que np sea -1 , es que es un campo de una tupla o indice de array y por tanto no hay qu meterlo en td.
    private int next; // apuntador a siguiente variable en cas de una tupla o argumentos o indice de array
    private int first; //apuntador a la lista de campos, o argumentos, o indices

    
    public Data(String id, IdDescripcion desc, int np, int next, int first){
        this.id = id;
        this.descripcion = desc;
        this.np = np;
        this.next = next; 
        this.first = first;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdDescripcion getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(IdDescripcion descripcion) {
        this.descripcion = descripcion;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getNp() {
        return np;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
