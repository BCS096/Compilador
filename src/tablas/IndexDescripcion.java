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
public class IndexDescripcion extends IdDescripcion{
    
    private int dim; // numero de la variable que está en la tabla de variables que contiene la dimension en bytes de este indice
    
    public IndexDescripcion(int dim) {
        super(TipoDescripcion.dindex);
        this.dim = dim;
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }
   
    
}
