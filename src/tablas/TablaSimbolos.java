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
//La función 'set' es confusión, ya que parece que pone algo, pero solo añade con el add de ArrayList...
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
        ta.set(n, 0);
    }

    public void vaciar() {
        n = 0;
        ta = new TablaAmbitos(); //??
        te = new TablaExpansion();
        td = new TablaDescripcion();
        ta.set(n, 0);
        n++;
        ta.set(n, -1);//esto estaba a 0, pero no tenía sentido. Si lo hacemos con 0 cambiar los -1 (explain Coti)
    }

    public void entrarBloque() {
        this.n++;
        this.ta.set(n, -1);
    }

    public void poner(String id, IdDescripcion desc) {
        Data d = td.get(id); //useless pero lo dejo por lo que sea (useless por si se da el caso de declarado en scope anterior)
        //Si existe la descripcion en la td, se hacen comprobaciones, si no es que simplemente es nueva y pasando de todo.
        if (td.existe(id)) {
            if (d.getNp() == n) {
                throw new UnsupportedOperationException("This symbol has already been declared in this scope");
                //lanzar excepcion
                //simbolo ya declarada en este ambito
            }
            if (d != null) {
                //Si está en un ámbito inferior, necesitamos 'aplastar' esa descripción del ámbito (copiarla a la te) y añadir la nueva
                if (d.getNp() < n) {
                    int idx = n;
                    //ESPERO QUE nuevaEntrada ME EVITE ESTO @COTI si es así bien hecho, si no explicamela xd
                    //Mientras tengamos scopes que no contienen punteros a la tabla de expansión
                    while (ta.get(idx) == -1) {
                        idx--;
                    }
                    idx++; //Ahora tenemos en idx el siguiente en la te

                    ta.nuevaEntrada(idx);

                    Data old = td.get(id);//Tendrá el mismo id la nueva y la antigua
                    te.put(idx, old);

                    //Y ahora la nueva en la td
                    td.put(id, d);
                } else {
                    //Idxe = ta[n], idxe = n, idxe = n+1, ta[n] = idxe.
                    //Si no se da el caso de que sea menor a n, y no es igual pq es lo primero que se comprueba, solo puede ser este caso. Se coloca sin más
                    td.put(id, d);
                }
            } else {
                td.put(id, d);
            }
        }
    }
    
    public void salirBloque() {
        // Ambito -1 no existe
        if (this.n == 0) {
            throw new UnsupportedOperationException("Compiler error, you have reached the end of the beginning... (too many blocks left)");
        }
        int posicionActual = ta.get(n);
        int posicionAnterior = ta.get(n - 1);
        //Básicamente el último ámbito declarado tiene que ser n-2, ya que 0 y 1 los ponemos al princpio :)
        int ultimoAmbitoDeclarado = n - 2;

        // Buscamos el último ámbito que escribió a la te (será el que no tenga un -1 y no puede ser el actual pq entonces no escribe a la te)
        while (posicionAnterior == -1) {
            posicionAnterior = ta.get(ultimoAmbitoDeclarado);
            ultimoAmbitoDeclarado--;
        }

        // Movemos los datos de la te de vuelta a la td:
        for (int i = posicionAnterior + 1; i <= posicionAnterior; i++) {
            Data dato = te.get(i);
            if (dato.getNp() != -1) {
                // Me ahorro el poner cosas pq Data es común a todas las tablas verdad? :) el simple put (que se tiene que crear) lo hace
                td.put(dato.getId(), dato);
            }
        }
        n--;
    }
    
    //TODO: Hacer que las tuplas se hagan bien? lo de que el primero en td y los demás tmb pero apuntando a los siguientes bla bla (arriba tmb?)
    public void putParam(String idProcedure, String idParam, Data data) {
        Data dataDescripcion = td.get(idProcedure);
        if (dataDescripcion != null) {
            IdDescripcion desc = dataDescripcion.getDescripcion();
            if (desc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dproc
                    && desc.getTipoDescripcion() != IdDescripcion.TipoDescripcion.dfunc) {
                System.out.println("ERROR. Put parameter not passed to a procedure or function");
            } else {
                int idx_p = dataDescripcion.getNp();
                int idx_ep = -1;
                while (idx_p != -1 && !(te.get(idx_p).getId().equals(idParam))) {
                    idx_ep = idx_p;
                    idx_p = te.get(idx_p).getNp();
                }
                if (idx_p != -1) {
                    System.out.println("ERROR. There's already a parameter with id: " + idParam + " . putParam");
                } else {
                    idx_p = ta.get(n) + 1;
                    ta.set(n, idx_p);
                    boolean tupla = dataDescripcion.getDescripcion().getTipoDescripcion() == IdDescripcion.TipoDescripcion.dtupel;
                    Data nuevoElemento = new Data();
                    nuevoElemento.setDescripcion(desc);
                    nuevoElemento.setId(idParam);
                    nuevoElemento.setIdParent(idProcedure);
                    nuevoElemento.setNext(-1);
                    nuevoElemento.setNp(n);
                    nuevoElemento.setTupel(tupla);
                    te.put(idx_p, nuevoElemento);

                    if (idx_ep == -1) {
                        dataDescripcion.setNp(idx_p);
                        td.put(idProcedure, dataDescripcion);
                    } else {
                        Data pointerToUpdate = te.get(idx_ep);
                        pointerToUpdate.setNp(idx_p);
                        te.put(idx_ep, pointerToUpdate);
                    }
                }
            }
        } else {
            System.out.println("ERROR. putParam, param does not exists: " + idParam);
        }
    }

    public Data getFirstParam(String idProcedure) {
        Data descripcion = td.get(idProcedure);
        IdDescripcion.TipoDescripcion tipoDescripcion = descripcion.getDescripcion().getTipoDescripcion();
        if (td.existe(idProcedure) && (tipoDescripcion == IdDescripcion.TipoDescripcion.dproc 
                || tipoDescripcion == IdDescripcion.TipoDescripcion.dfunc)) {
            if (descripcion.getNp() != -1) {
                return te.get(descripcion.getNp());
            }
        }
        return null;
    }

    public Data getNextParam(Data elem) {
        if (elem.getNp() != -1) {
            return te.get(elem.getNp());
        }
        return null;
    }
    
    public int getActual(){
        return this.n;
    }
}
