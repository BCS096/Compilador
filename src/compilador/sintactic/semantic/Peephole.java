/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.semantic;

import compilador.sintactic.semantic.Operator3Address.CastType;
import compilador.sintactic.semantic.Operator3Address.Type;
import java.util.ArrayList;
import types.TypeEnum;

/**
 *
 * @author tomeu
 */
public class Peephole {

    private final ArrayList<Instruction3Address> code;

    public Peephole(CodeGeneration3Address cg) {
        this.code = new ArrayList<>(cg.getInstruccions());
    }

    //checked
    public boolean brancamentAdjacent() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            if (isIf(code.get(i).getInstructionType())) { //if cond goto e1
                //guardo la etiqueta
                Operator3Address e1 = code.get(i).getOperators()[2];
                int posIf = i;
                i++;
                if (i < code.size() && code.get(i).getInstructionType() == InstructionType.GOTO) { //goto e2
                    //guardamos la etiqueta del goto
                    Operator3Address e2 = code.get(i).getOperators()[2];
                    int posGoto = i;
                    i++;
                    if (i < code.size() && code.get(i).getInstructionType() == InstructionType.SKIP && code.get(i).getOperators()[2].getLabel().equals(e1.getLabel())) { //e1 : skip

                        code.get(posIf).setInstructionType(negCond(code.get(posIf).getInstructionType()));
                        code.get(posIf).setOperator(2, e2);
                        code.remove(posGoto);
                        canvi = true;
                        //miramos si podemos borrar el e1:skip
                        boolean borrar = true;
                        for (int j = 0; j < code.size(); j++) {
                            if ((isIf(code.get(j).getInstructionType()) || code.get(j).getInstructionType() == InstructionType.GOTO) && code.get(j).getOperators()[2].getLabel().equals(e1.getLabel())) {
                                borrar = false;
                                break;
                            }
                        }
                        if (borrar) {
                            //el skip e1 ahora estará en la posición del goto porque como hemos borrado el goto, todas las
                            //instrucciones por delante del goto han retrocedido una posición.
                            code.remove(posGoto);
                        }
                    }
                }
            }
        }
        return canvi;
    }

    //checked
    public boolean brancamentSobreBrancament() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            if (isIf(code.get(i).getInstructionType())) {
                int posIf = i;
                Operator3Address e1 = code.get(i).getOperators()[2];
                for (int j = i + 1; j < code.size(); j++) {
                    if (code.get(j).getInstructionType() == InstructionType.SKIP && code.get(j).getOperators()[2].getLabel().equals(e1.getLabel())) {
                        if (j + 1 < code.size() && code.get(j + 1).getInstructionType() == InstructionType.GOTO) {
                            code.get(posIf).setOperator(2, code.get(j + 1).getOperators()[2]);
                            boolean borrar = true;
                            for (int k = 0; k < code.size(); k++) {
                                if ((isIf(code.get(k).getInstructionType()) || code.get(k).getInstructionType() == InstructionType.GOTO) && code.get(k).getOperators()[2].getLabel().equals(e1.getLabel())) {
                                    borrar = false;
                                    break;
                                }
                            }
                            if (borrar) {
                                canvi = true;
                                code.remove(j);
                            }
                        }
                    }
                }
            }
        }
        return canvi;
    }

    //checked
    public boolean operacioConstant1() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            if (isOp(code.get(i).getInstructionType()) && code.get(i).getOperators()[0].getType() == Type.literal
                    && code.get(i).getOperators()[1].getType() == Type.literal) {
                canvi = true;
                int a = (int) code.get(i).getOperators()[0].getLiteral();
                int b = (int) code.get(i).getOperators()[1].getLiteral();
                switch (code.get(i).getInstructionType()) {
                    case ADD:
                        a = a + b;
                        break;
                    case DIV:
                        a = a / b;
                        break;
                    case MOD:
                        a = a % b;
                        break;
                    case MUL:
                        a = a * b;
                        break;
                    case SUB:
                        a = a - b;
                        break;
                }
                code.get(i).setInstructionType(InstructionType.CLONE);
                code.get(i).setOperator(0, new Operator3Address(a, CastType.INT));
                code.get(i).setOperator(1, null);
            }
        }
        return canvi;
    }

    //checked
    public boolean operacioConstant2() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            if (isIf(code.get(i).getInstructionType()) && code.get(i).getOperators()[0].getType() == Type.literal
                    && code.get(i).getOperators()[1].getType() == Type.literal) {
                boolean res = false;
                switch (code.get(i).getOperators()[0].getCastType()) {
                    case INT:
                    case BOOL:
                        int a;
                        int b;
                        if (code.get(i).getOperators()[0].getCastType() == CastType.BOOL) {
                            if ((boolean) code.get(i).getOperators()[0].getLiteral() == true) {
                                a = 1;
                            } else {
                                a = 0;
                            }
                        } else {
                            a = (int) code.get(i).getOperators()[0].getLiteral();
                        }
                        if (code.get(i).getOperators()[1].getCastType() == CastType.BOOL) {
                            if ((boolean) code.get(i).getOperators()[1].getLiteral() == true) {
                                b = 1;
                            } else {
                                b = 0;
                            }
                        } else {
                            b = (int) code.get(i).getOperators()[1].getLiteral();
                        }
                        switch (code.get(i).getInstructionType()) {
                            case IFEQ:
                                if (a == b) {
                                    res = true;
                                }
                                break;
                            case IFGE:
                                if (a >= b) {
                                    res = true;
                                }
                                break;
                            case IFGT:
                                if (a > b) {
                                    res = true;
                                }
                                break;
                            case IFLE:
                                if (a <= b) {
                                    res = true;
                                }
                                break;
                            case IFLT:
                                if (a < b) {
                                    res = true;
                                }
                                break;
                            case IFNE:
                                if (a != b) {
                                    res = true;
                                }
                                break;
                        }
                        break;
                    case CHAR:
                        char c = (char) code.get(i).getOperators()[0].getLiteral();
                        char d = (char) code.get(i).getOperators()[1].getLiteral();
                        switch (code.get(i).getInstructionType()) {
                            case IFEQ:
                                if (c == d) {
                                    res = true;
                                }
                                break;
                            case IFGE:
                                if (c >= d) {
                                    res = true;
                                }
                                break;
                            case IFGT:
                                if (c > d) {
                                    res = true;
                                }
                                break;
                            case IFLE:
                                if (c <= d) {
                                    res = true;
                                }
                                break;
                            case IFLT:
                                if (c < d) {
                                    res = true;
                                }
                                break;
                            case IFNE:
                                if (c != d) {
                                    res = true;
                                }
                                break;
                        }
                        break;
                    case STRING:
                        String g = (String) code.get(i).getOperators()[0].getLiteral();
                        String h = (String) code.get(i).getOperators()[1].getLiteral();
                        switch (code.get(i).getInstructionType()) {
                            case IFEQ:
                                if (g.equals(h)) {
                                    res = true;
                                }
                                break;
                            case IFNE:
                                if (!g.equals(h)) {
                                    res = true;
                                }
                                break;
                        }
                        break;
                }
                if (res) {
                    canvi = true;
                    code.get(i).setInstructionType(InstructionType.GOTO);
                    code.get(i).setOperator(0, null);
                    code.get(i).setOperator(1, null);
                } else {
                    canvi = true;
                    code.remove(i);
                }
            }
        }
        return canvi;
    }

    //checked
    public boolean codiInaccesible1() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            //borrar goto innecesarios
            if (code.get(i).getInstructionType() == InstructionType.GOTO
                    && i + 1 < code.size() && code.get(i + 1).getInstructionType() == InstructionType.GOTO) {
                canvi = true;
                code.remove(i + 1);
            }
        }
        return canvi;
    }

    //checked
    public boolean codiInaccesible2() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            if (code.get(i).getInstructionType() == InstructionType.GOTO) {
                String label = code.get(i).getOperators()[2].getLabel();
                for (int j = i + 1; j < code.size(); j++) {
                    if (code.get(j).getInstructionType() == InstructionType.SKIP) {
                        if (code.get(j).getOperators()[2].getLabel().equals(label)) {
                            //borrar codigo desde el goto e1 hasta skip e1
                            for (int k = i + 1; k < j; k++) {
                                canvi = true;
                                code.remove(i + 1);
                            }
                            break;
                        } else {
                            //mirar si tiene un goto en el codigo
                            String aux = code.get(j).getOperators()[2].getLabel();
                            boolean trobat = false;
                            for (int k = 0; k < code.size(); k++) {
                                if ((code.get(k).getInstructionType() == InstructionType.GOTO || isIf(code.get(k).getInstructionType()))
                                        && code.get(k).getOperators()[2].getLabel().equals(aux)) {
                                    trobat = true;
                                    break;
                                }
                            }
                            if (trobat) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return canvi;
    }

    //checked
    public boolean assignacioDiferida() {
        boolean canvi = false;
        for (int i = 0; i < code.size(); i++) {
            if (isOp(code.get(i).getInstructionType()) || code.get(i).getInstructionType() == InstructionType.CLONE
                    || code.get(i).getInstructionType() == InstructionType.NEG || code.get(i).getInstructionType() == InstructionType.INDVALUE) {
                int res = code.get(i).getOperators()[2].getReference();
                int cont = 0;
                int pos = -1;
                int j;
                for (j = 1; j < code.size(); j++) {
                    if (j != i) {
                        if ((code.get(j).getOperators()[0] != null && code.get(j).getOperators()[0].getType() == Type.reference && code.get(j).getOperators()[0].getReference() == res)
                                || (code.get(j).getOperators()[1] != null && code.get(j).getOperators()[1].getType() == Type.reference && code.get(j).getOperators()[1].getReference() == res)
                                || (code.get(j).getInstructionType() == InstructionType.SIMPLEPARAM
                                && code.get(j).getOperators()[2] != null && code.get(j).getOperators()[2].getReference() == res)
                                || (code.get(j).getInstructionType() == InstructionType.RETURN
                                && code.get(j).getOperators()[2] != null && code.get(j).getOperators()[2].getReference() == res)) {
                            if (cont == 1) {
                                cont++;
                                break;
                            } else {
                                cont++;
                                pos = j;
                            }
                        }
                        if ((isOp(code.get(j).getInstructionType()) || code.get(j).getInstructionType() == InstructionType.CLONE
                                || code.get(j).getInstructionType() == InstructionType.NEG || code.get(j).getInstructionType() == InstructionType.INDVALUE) && code.get(j).getOperators()[2].getReference() == res) {
                            cont = 2;
                            break;
                        }
                    }

                }
                if (cont == 1) {
                    if (code.get(i).getInstructionType() == InstructionType.CLONE || code.get(i).getInstructionType() == InstructionType.NEG) {
                        Operator3Address op = code.get(i).getOperators()[0];
                        if (code.get(pos).getInstructionType() == InstructionType.CLONE || code.get(pos).getInstructionType() == InstructionType.NEG
                                /*|| code.get(pos).getInstructionType() == InstructionType.PRINT || code.get(pos).getInstructionType() == InstructionType.PRINTLN*/
                                || code.get(pos).getInstructionType() == InstructionType.READ) {
                            code.get(pos).setOperator(0, op);
                            code.remove(i);
                            canvi = true;
                            i--;
                        } /*else if (code.get(pos).getInstructionType() == InstructionType.SIMPLEPARAM ||  code.get(pos).getInstructionType() == InstructionType.RETURN) {
                            code.get(pos).setOperator(2, op);
                            code.remove(i);
                            canvi = true;
                            i--;
                        }*/ else if (code.get(pos).getInstructionType() == InstructionType.PRINT || code.get(pos).getInstructionType() == InstructionType.PRINTLN) {
                        } else {
                            if (code.get(pos).getOperators()[0] != null && code.get(pos).getOperators()[0].getType() == Type.reference && code.get(pos).getOperators()[0].getReference() == res) {
                                code.get(pos).setOperator(0, op);
                            } else { // sera el otro operador el que ha coincidido
                                code.get(pos).setOperator(1, op);
                            }
                            code.remove(i);
                            canvi = true;
                            i--;
                        }
                    } else {
                        Operator3Address op1 = code.get(i).getOperators()[0];
                        Operator3Address op2 = code.get(i).getOperators()[1];
                        if (code.get(pos).getInstructionType() == InstructionType.CLONE) {
                            code.get(pos).setInstructionType(code.get(i).getInstructionType());
                            code.get(pos).setOperator(0, op1);
                            code.get(pos).setOperator(1, op2);
                            code.remove(i);
                            i--;
                            canvi = true;
                        }
                    }
                }
            }
        }
        return canvi;
    }

    //checked
    private boolean isIf(InstructionType inst) {
        return inst == InstructionType.IFEQ || inst == InstructionType.IFGE || inst == InstructionType.IFGT
                || inst == InstructionType.IFLE || inst == InstructionType.IFLT || inst == InstructionType.IFNE;
    }

    //checked
    private InstructionType negCond(InstructionType inst) {
        switch (inst) {
            case IFLT:
                return InstructionType.IFGE;
            case IFLE:
                return InstructionType.IFGT;
            case IFGT:
                return InstructionType.IFLE;
            case IFGE:
                return InstructionType.IFLT;
            case IFEQ:
                return InstructionType.IFNE;
            case IFNE:
                return InstructionType.IFEQ;
            default:
                return null;
        }
    }

    //checked
    private boolean isOp(InstructionType inst) {
        return inst == InstructionType.ADD || inst == InstructionType.DIV || inst == InstructionType.MOD
                || inst == InstructionType.MUL || inst == InstructionType.SUB;
    }

    public ArrayList<Instruction3Address> getCode() {
        return code;
    }
}
