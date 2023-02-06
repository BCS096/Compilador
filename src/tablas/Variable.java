/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import types.TypeEnum;

/**
 *
 * @author emanu
 */
public class Variable {

    private final String id;
    private final TipoVariable tipoVar;
    private final String idProc;
    private final int bytes;
    private final TypeEnum tipo;
    private final boolean isArray;
    private final boolean isTupel;

    private int offset;

    public enum TipoVariable {
        PARAM,
        VARIABLE
    }

    public Variable(String id, TipoVariable tipoVar, TypeEnum tipo, String idProc, int bytes, boolean isArray, boolean isTupel) {
        this.id = id;
        this.idProc = idProc;
        this.tipoVar = tipoVar;
        this.bytes = bytes;
        this.tipo = tipo;
        this.isArray = isArray;
        this.isTupel = isTupel;
    }

    public boolean isTupel() {
        return isTupel;
    }

    public TipoVariable getTipoVariable() {
        return this.tipoVar;
    }

    public TypeEnum getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public int getBytes() {
        return bytes;
    }

    public int getOffset() {
        return offset;
    }

    public String getIdProcedimiento() {
        return idProc;
    }

    public boolean isArray() {
        return this.isArray;
    }

    /**
     * Modify current variable offset
     *
     * - Passed as parameter: positive value - Passed as variable: negative
     * value - Passed as constant: Current value
     *
     * Para bloques dinámicos
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    @Override
    public String toString(){
        return "Id variable: " + id + ", tipo: " + tipoVar + ", offset: " + offset + ", del procedimiento: " + idProc + " y con tamaño de bytes: " + bytes;
    }
}
