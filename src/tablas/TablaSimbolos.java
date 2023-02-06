/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import compilador.sintactic.Parser;
import compilador.sintactic.nodes.BaseNode;
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
    private final Parser parser;

    public TablaSimbolos(Parser parser) {
        n = 0;
        ta = new TablaAmbitos();
        te = new TablaExpansion();
        td = new TablaDescripcion();
        this.parser = parser;
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

    //checked
    public IdDescripcion consultaId(String id) {
        if (td.existe(id)) {
            return td.get(id).getDescripcion();
        }
        return null;
    }

    //checked
    public void entrarBloque() {
        this.n++;
        this.ta.set(n, this.ta.get(n - 1));
    }

    //checked
    public boolean poner(String id, IdDescripcion d, BaseNode node) {
        if (td.existe(id)) {
            if (td.get(id).getNp() == n) {
                //throw new UnsupportedOperationException("Símbolo ya declarado en el ámbito actual");
                parser.report_error("Símbolo ya declarado en el ámbito actual", node);
                return true;
            }
            ta.nuevaEntrada(n);
            te.put(ta.get(n), td.get(id));
        }
        td.put(id, new Data(id, d, n, -1, -1));
        return false;
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

    //checked
    public void ponerCampo(String idTupel, String idCampo, IdDescripcion dCampo, BaseNode node) {
        if (!td.existe(idTupel)) {
            parser.report_error("No existe la tupla con este nombre: " + idTupel, node);
            //throw new UnsupportedOperationException("No existe la tupla con este nombre: " + idTupel);
        } else {
            IdDescripcion tupelDesc = td.get(idTupel).getDescripcion();
            if (tupelDesc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dtupel) {
                parser.report_error("Se intenta poner un campo en una variable que no corresponde a una tupla", node);
                //throw new UnsupportedOperationException("Se intenta poner un campo en una variable que no corresponde a una tupla");
            } else {
                int idxe = td.get(idTupel).getFirst();
                int idxep = -1;
                while (idxe != -1 && !(te.get(idxe).getId().equals(idCampo))) {
                    idxep = idxe;
                    idxe = te.get(idxe).getNext();
                }
                if (idxe != -1) {
                    parser.report_error("Ya existe un campo con este nombre: " + idCampo, node);
                    //throw new UnsupportedOperationException("Ya existe un campo con este nombre: " + idCampo);
                } else {
                    ta.nuevaEntrada(n);
                    idxe = ta.get(n);
                    Data data = new Data(idCampo, dCampo, -1, -1, -1);
                    te.put(idxe, data);
                    if (idxep == -1) {
                        td.get(idTupel).setFirst(idxe);
                    } else {
                        te.get(idxep).setNext(idxe);
                    }
                }
            }
        }
    }

    //checked
    public IdDescripcion consultarCampo(String idTupel, String idCampo, BaseNode node) {
        if (!td.existe(idTupel)) {
            //throw new UnsupportedOperationException("No existe la tupla con este nombre: " + idTupel);
            parser.report_error("No existe la tupla con este nombre: " + idTupel, node);
        } else {
            IdDescripcion d = td.get(idTupel).getDescripcion();
            if (d.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dtupel) {
                //throw new UnsupportedOperationException("Se intenta consultar un campo en una variable que no corresponde a una tupla");
                parser.report_error("Se intenta consultar un campo en una variable que no corresponde a una tupla", node);
            } else {
                int i = td.get(idTupel).getFirst();
                while (i != -1 && !(te.get(i).getId().equals(idCampo))) {
                    i = te.get(i).getNext();
                }
                if (i != -1) {
                    return te.get(i).getDescripcion();
                }
            }
        }

        return null;
    }

    //checked
    public void ponerIndice(String id, IdDescripcion d, BaseNode node) {
        if (!td.existe(id)) {
            parser.report_error("No existe el array con este nombre: " + id, node);
            //throw new UnsupportedOperationException("No existe el array con este nombre: " + id);
        } else {
            IdDescripcion darray = td.get(id).getDescripcion();
            if (darray.getTipoDescripcion() != IdDescripcion.TipoDescripcion.darray) {
                //throw new UnsupportedOperationException("No es un array");
                parser.report_error("No es un array", node);
            } else {
                int idxe = td.get(id).getFirst();
                int idxep = -1;
                while (idxe != -1) {
                    idxep = idxe;
                    idxe = te.get(idxe).getNext();
                }
                ta.nuevaEntrada(n);
                Data data = new Data("", d, -1, -1, -1);
                te.put(ta.get(n), data);
                if (idxep == -1) {
                    td.get(id).setFirst(ta.get(n));
                } else {
                    te.get(idxep).setNext(ta.get(n));
                }
            }

        }
    }

    //checked
    public int firstIndice(String id, BaseNode node) {
        if (!td.existe(id)) {
            //throw new UnsupportedOperationException("No existe el array con este nombre: " + id);
            parser.report_error("No existe el array con este nombre ", node);
        } else {
            IdDescripcion darray = td.get(id).getDescripcion();
            if (darray.getTipoDescripcion() != IdDescripcion.TipoDescripcion.darray) {
                //throw new UnsupportedOperationException("No es un array");
                parser.report_error("No es una array", node);
            }
            return td.get(id).getFirst();
        }
        return -1;
    }

    //checked
    public int firstParam(String id, BaseNode node) {
        if (!td.existe(id)) {
            //throw new UnsupportedOperationException("No existe el procedimiento/función con este nombre: " + id);
            parser.report_error("No existe el procedimiento/función con este nombre: ", node);
            return -1;
        }
        IdDescripcion dProc = td.get(id).getDescripcion();
        if (dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dproc
                && dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dfunc) {
            //throw new UnsupportedOperationException("No es un procedimiento/función");
            parser.report_error("No es un procedimiento/función", node);
            return -1;
        }
        return td.get(id).getFirst();
    }

    //checked
    public int firstCampo(String id, BaseNode node) {
        if (!td.existe(id)) {
            //throw new UnsupportedOperationException("No existe la tupla con este nombre: " + id);
            parser.report_error("No existe la tupla con este nombre: " + id, node);
            return -1;
        }
        IdDescripcion dtupla = td.get(id).getDescripcion();
        if (dtupla.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dtupel) {
            //throw new UnsupportedOperationException("No es una tupla");
            parser.report_error("No es una tupla", node);
            return -1;
        }
        return td.get(id).getFirst();
    }

    //checked
    public ArrayList<ArgDescripcion> consultarParams(String id, BaseNode node) {
        ArrayList<ArgDescripcion> res = new ArrayList<>();
        int actual = firstParam(id, node);
        if (actual == -1) {
            return null;
        }
        //meto el primero
        res.add((ArgDescripcion) consultarTe(actual));
        if (last(actual)) {
            //solo hay un elemento
            return res;
        }
        while (!last(actual)) {
            actual = next(actual, node);
            res.add((ArgDescripcion) consultarTe(actual));
        }
        return res;
    }

    //checked
    public ArrayList<CampoDescripcion> consultarCampos(String id, BaseNode node) {
        ArrayList<CampoDescripcion> res = new ArrayList<>();
        int actual = firstCampo(id, node);
        if (actual == -1) {
            return null;
        }
        //meto el primero
        res.add((CampoDescripcion) consultarTe(actual));
        if (last(actual)) {
            //solo hay un elemento
            return res;
        }
        while (!last(actual)) {
            actual = next(actual, node);
            res.add((CampoDescripcion) consultarTe(actual));
        }
        return res;
    }

    //checked
    public int next(int idx, BaseNode node) { //puede servir tanto para indices,campos,parametros
        if (te.get(idx).getNext() == -1) {
            parser.report_error("El indice/campo/parametro actual es el último", node);
            //throw new UnsupportedOperationException("El indice/campo/parametro actual es el último");
        }
        return te.get(idx).getNext();
    }

    //checked
    public boolean last(int idx) { //puede servir tanto para indices,campos,parametros
        return te.get(idx).getNext() == -1;
    }

    //checked
    //para consultar en la tabla de expansion los campos,indices y parametros que no se copian en la tabla de descripciones
    public IdDescripcion consultarTe(int idx) {
        return te.get(idx).getDescripcion();
    }

    //checked
    public void ponerParam(String idProc, String idParam, IdDescripcion d, BaseNode node) {
        if (!td.existe(idProc)) {
            //throw new UnsupportedOperationException("No existe el procedimiento/función con este nombre: " + idProc);
            parser.report_error("No existe el procedimiento/función con este nombre: " + idProc, node);
        } else {
            IdDescripcion dProc = td.get(idProc).getDescripcion();
            if (dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dproc
                    && dProc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dfunc) {
                //throw new UnsupportedOperationException("Intentando pasar parámetros a elemento que no es ni procedimiento ni función!");
                parser.report_error("Intentando pasar parámetros a elemento que no es ni procedimiento ni función!", node);
            } else {
                int idxe = td.get(idProc).getFirst();
                int idxep = -1;
                while (idxe != -1 && !(te.get(idxe).getId().equals(idParam))) {
                    idxep = idxe;
                    idxe = te.get(idxe).getNext();
                }
                if (idxe != -1) {
                    //throw new UnsupportedOperationException("Ya hay un parámetro en este procedimiento/funcion con el mismo nombre");
                    parser.report_error("Ya hay un parámetro en este procedimiento/funcion con el mismo nombre", node);
                } else {
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
            }
        }
    }

    //checked
    public int getActual() {
        return this.n;
    }

    //checked
    public ArrayList<IndexDescripcion> consultarIndices(String idArray, BaseNode node) {
        ArrayList<IndexDescripcion> res = new ArrayList<>();
        int actual = firstIndice(idArray, node);
        if (actual == -1) {
            return null;
        }
        //meto el primero
        res.add((IndexDescripcion) consultarTe(actual));
        if (last(actual)) {
            //solo hay un elemento
            return res;
        }
        while (!last(actual)) {
            actual = next(actual, node);
            res.add((IndexDescripcion) consultarTe(actual));
        }
        return res;
    }

}
