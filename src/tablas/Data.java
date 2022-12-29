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
    private String idParent; //identificador del proc/func/tupel en caso que este id pertenezca a uno de estos
    private IdDescripcion descripcion;
    private int np;
    private int next; // apuntador a siguiente variable en cas de una tupla o argumentos
    private boolean descriptionCopy; // se tiene que copiar a la tabla de descripción? (caso tupla)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public IdDescripcion getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(IdDescripcion descripcion) {
        this.descripcion = descripcion;
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
