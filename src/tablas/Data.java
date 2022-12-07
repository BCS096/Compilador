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
    private int idAnt; // misma variable en un ambito anterior

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

    public int getIdAnt() {
        return idAnt;
    }

    public void setIdAnt(int idAnt) {
        this.idAnt = idAnt;
    }
}
