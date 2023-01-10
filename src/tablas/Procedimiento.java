/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.util.ArrayList;
import types.TypeEnum;

/**
 *
 * @author emanu
 */
//Un procedimiento contiene (o no) parámetros que vienen definidos por una variable con su tipo (necesario más adelante) -> Objeto Parámetro
public class Procedimiento {
    private final int ambito;
    private final String etiquetaInicial;
    private final ArrayList<ObjetoParametro> parametros;
    private int numParams;
    private int localVariablesSize;

    public int getLocalVariablesSize() {
        return this.localVariablesSize;
    }

    public void setLocalVariablesSize(int localVariablesSize) {
        this.localVariablesSize = localVariablesSize;
    }

    /**
    * @param ambito: El ambito donde se declara
    * @param etiquetaInicial: la etiqueta inicial, es decir donde empieza el procedimiento
    * @param numParams: Nombre de parámetros que tendrá el procedimiento
    */
    public Procedimiento(int ambito, String etiquetaInicial, int numParams) {
        this.ambito = ambito;
        this.etiquetaInicial = etiquetaInicial;
        this.parametros = new ArrayList<>();
        this.numParams = numParams;
    }

    public int getAmbito() {
        return ambito;
    }

    public String getEtiquetaInicial() {
        return etiquetaInicial;
    }

    public ArrayList<ObjetoParametro> getParametros() {
        return parametros;
    }

    public int getNumberParameters() {
        return numParams;
    }

    public void addParam(ObjetoParametro parametro) {
        this.parametros.add(parametro);
    }

    public void setNumberParameters(int numParams) {
        this.numParams = numParams;
    }
}

class ObjetoParametro {

    private final int numeroVariable;
    private final TypeEnum tipo;

    public ObjetoParametro(int numeroVariable, TypeEnum tipo) {
        this.numeroVariable = numeroVariable;
        this.tipo = tipo;
    }

    public int getNumeroVariable() {
        return this.numeroVariable;
    }

    public TypeEnum getTipo() {
        return this.tipo;
    }
}
