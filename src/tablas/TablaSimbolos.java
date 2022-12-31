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
                    //TODO: Crear el método, no lo hago por si me estoy perdiendo algo que tenías planeado @Coti
                    td.put(id, d.getDescripcion());
                } else {
                    //Idxe = ta[n], idxe = n, idxe = n+1, ta[n] = idxe.
                    //Si no se da el caso de que sea menor a n, y no es igual pq es lo primero que se comprueba, solo puede ser este caso. Se coloca sin más
                    td.put(id, d.getDescripcion());
                }
            } else {
                td.put(id, d.getDescripcion());
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
    
    //TODO: COSAS PARA PARÁMETROS, el método de poner un parámetro y cogerlos (el primero y los demás) para las procedures (2 metodillos que no me daba la vida)
    
    public int getActual(){
        return this.n;
    }
}
