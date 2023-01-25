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
public class TablaSimbolos {

    private int n;
    private TablaAmbitos ta;
    private TablaExpansion te;
    private TablaDescripcion td;

    public TablaSimbolos() {
        n = 0;
        ta = new TablaAmbitos();
        te = new TablaExpansion();
        td = new TablaDescripcion();
        ta.set(n, 0);
        n++;
        ta.set(n, -1);
    }

    public void vaciar() {
        n = 0;
        ta = new TablaAmbitos();
        te = new TablaExpansion();
        td = new TablaDescripcion();
        ta.set(n, 0);
        n++;
        ta.set(n, -1);
    }

    public IdDescripcion consultaId(String id) {
        return td.get(id).getDescripcion();
    }

    public void entrarBloque() {
        this.n++;
        this.ta.set(n, this.ta.get(n - 1));
    }

    public void ponerM(String id, IdDescripcion d) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int np = td.get(id).getNp();
        if (td.existe(id)) {

            if (np == n) {
                throw new UnsupportedOperationException("Símbolo ya declarado en el ámbito actual");
            } else if (n > np) {
                ta.nuevaEntrada(n);
                te.put(ta.get(n), td.get(id));
            }
        } else {
            Data data = new Data(id, d, n, -1, -1);
            td.put(id, data);
        }
    }

    //nunca habrá que controloar n< o n> ya que hacemos bien los entrar, salir bloque :)
    public void poner(String id, IdDescripcion d) {
        if (td.existe(id)) {
            if (td.get(id).getNp() == n) {
                throw new UnsupportedOperationException("Símbolo ya declarado en el ámbito actual");
            }
            ta.nuevaEntrada(n);
            te.put(n, td.get(id));
        }

        td.put(id, new Data(id, d, n, -1, -1));
    }

    public void salirBloque() {
        if (n == 0) {
            throw new UnsupportedOperationException("Error del compilador");
        }
        int lini = ta.get(n);
        n--;
        int lfi = ta.get(n);
        while (lini != lfi) {
            if (te.get(lini).getNp() != -1) {
                String id = te.get(lini).getId();
                td.put(id, te.get(lini));
                if (te.get(lini).getDescripcion().getTipoDescripcion() == IdDescripcion.TipoDescripcion.darray
                        || te.get(lini).getDescripcion().getTipoDescripcion() == IdDescripcion.TipoDescripcion.dtupel
                        || te.get(lini).getDescripcion().getTipoDescripcion() == IdDescripcion.TipoDescripcion.dproc
                        || te.get(lini).getDescripcion().getTipoDescripcion() == IdDescripcion.TipoDescripcion.dfunc) {
                    td.get(id).setFirst(te.get(lini).getFirst());
                }
            }
            lini--;
        }
    }

    public void ponerCampo(String idTupel, String idCampo, IdDescripcion dCampo) {
        if (!td.existe(idTupel)) {
            throw new UnsupportedOperationException("No existe la tupla con este nombre: " + idTupel);
        }
        IdDescripcion tupelDesc = td.get(idTupel).getDescripcion();
        if (tupelDesc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dtupel) {
            throw new UnsupportedOperationException("Se intenta poner un campo en una variable que no corresponde a una tupla");
        }
        int i = td.get(idTupel).getFirst();
        while (i != -1 && !(te.get(i).getId().equals(idCampo))) {
            i = te.get(i).getNext();
        }
        if (i != -1) {
            throw new UnsupportedOperationException("Ya existe un campo con este nombre: " + idCampo);
        }
        ta.nuevaEntrada(n);
        Data data = new Data(idCampo, dCampo, -1, td.get(idTupel).getFirst(), -1);
        te.put(ta.get(n), data);
        td.get(idTupel).setFirst(ta.get(n));
    }

    public IdDescripcion consultarCampo(String idTupel, String idCampo) {
        if (td.existe(idTupel)) {
            throw new UnsupportedOperationException("No existe la tupla con este nombre: " + idTupel);
        }
        IdDescripcion d = td.get(idTupel).getDescripcion();
        if (d.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dtupel) {
            throw new UnsupportedOperationException("Se intenta consultar un campo en una variable que no corresponde a una tupla");
        }
        int i = td.get(idTupel).getFirst();
        while (i != -1 && !(te.get(i).getId().equals(idCampo))) {
            i = te.get(i).getNext();
        }
        if (i != -1) {
            return te.get(i).getDescripcion();
        }
        return null;
    }

    public void ponerIndice(String id, IdDescripcion d) {
        if (td.existe(id)) {
            throw new UnsupportedOperationException("No existe el array con este nombre: " + id);
        }
        IdDescripcion darray = td.get(id).getDescripcion();
        if (darray.getTipoDescripcion() != IdDescripcion.TipoDescripcion.darray) {
            throw new UnsupportedOperationException("No es un array");
        }
        int idxe = td.get(id).getFirst();
        int idxep = 0;
        while (idxe != -1) {
            idxep = idxe;
            idxe = te.get(idxe).getNext();
        }
        ta.nuevaEntrada(n);
        Data data = new Data("", d, -1, -1, -1);
        te.put(idxe + 1, data);
        if (idxep == -1) {
            td.get(id).setFirst(idxe + 1);
        } else {
            te.get(idxep).setNext(idxe + 1);
        }
    }

    public int firstIndice(String id) {
        if (td.existe(id)) {
            throw new UnsupportedOperationException("No existe el array con este nombre: " + id);
        }
        IdDescripcion darray = td.get(id).getDescripcion();
        if (darray.getTipoDescripcion() != IdDescripcion.TipoDescripcion.darray) {
            throw new UnsupportedOperationException("No es un array");
        }
        return td.get(id).getFirst();
    }

    public int firstParam(String id) {
        if (!td.existe(id)) {
            throw new UnsupportedOperationException("No existe el procedimiento/función con este nombre: " + id);
        }
        IdDescripcion dProc = td.get(id).getDescripcion();
        if (dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dproc
                && dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dfunc) {
            throw new UnsupportedOperationException("No es un procedimiento/función");
        }
        return td.get(id).getFirst();
    }

    public ArrayList<ArgDescripcion> consultarParams(String id) {
        ArrayList<ArgDescripcion> res = new ArrayList<>();
        int actual = firstParam(id);
        if (actual == -1) {
            return null;
        }
        while (!last(actual)) {
            actual = next(actual);
            res.add((ArgDescripcion) consultarTe(actual));
        }
        //ponemos el último param
        res.add((ArgDescripcion) consultarTe(actual));
        return res;
    }

    public int next(int idx) { //puede servir tanto para indices,campos,parametros
        if (te.get(idx).getNext() == -1) {
            throw new UnsupportedOperationException("El indice/campo/parametro actual es el último");
        }
        return te.get(idx).getNext();
    }

    public boolean last(int idx) { //puede servir tanto para indices,campos,parametros
        return te.get(idx).getNext() == -1;
    }

    //para consultar en la tabla de expansion los campos,indices y parametros que no se copian en la tabla de descripciones
    public IdDescripcion consultarTe(int idx) {
        return te.get(idx).getDescripcion();
    }

//    public ArrayList<> consultaIndices(String id) {
//        if (td.get(id).getDescripcion().getTipoDescripcion() == IdDescripcion.TipoDescripcion.darray) {
//            int idx = td.get(id).getFirst();
//            ArrayList<ArgDescripcion> res = new ArrayList<>();
//            int actual = firstParam(id);
//            if (actual == -1) {
//                return null;
//            }
//            while (!last(actual)) {
//                actual = next(actual);
//                res.add((ArgDescripcion) consultarTe(actual));
//            }
//            //ponemos el último param
//            res.add((ArgDescripcion) consultarTe(actual));
//            return res;
//        }
//    }

    public void ponerParam(String idProc, String idParam, IdDescripcion d) {
        //si existe, no existe GUAT
        if (!td.existe(idProc)) {
            throw new UnsupportedOperationException("No existe el procedimiento/función con este nombre: " + idProc);
        }
        IdDescripcion dProc = td.get(idProc).getDescripcion();
        if (dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dproc
                && dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dfunc) {
            throw new UnsupportedOperationException("Intentando pasar parámetros a elemento que no es ni procedimiento ni función!");
        }
        int idxe = td.get(idProc).getFirst();
        int idxep = -1;
        while (idxe != -1 && !(te.get(idxe).getId().equals(idParam))) {
            idxep = idxe;
            idxe = te.get(idxe).getNext();
        }
        if (idxe != -1) {
            throw new UnsupportedOperationException("Ya hay un parámetro en este procedimiento/funcion con el mismo nombre");
        }
        ta.nuevaEntrada(n);
        idxe = ta.get(n);
        Data data = new Data(idParam, d, -1, -1, -1);
        te.put(idxe, data);
        if (idxep == -1) {
            td.get(idProc).setFirst(idxe);
        } else {
            te.get(idxep).setNext(idxe);
        }
    }

    public int getActual() {
        return this.n;
    }

}
