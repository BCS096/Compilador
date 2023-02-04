/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.semantic;

import compilador.sintactic.semantic.Operator3Address.CastType;
import compilador.sintactic.semantic.Operator3Address.Type;
import java.util.ArrayList;

/**
 *
 * @author tomeu
 */
public class Peenhole {

    private final CodeGeneration3Address cg;
    private final ArrayList<Instruction3Address> code;
    
    public Peenhole(CodeGeneration3Address cg) {
        this.cg = cg;
        this.code = new ArrayList<>(cg.getInstruccions());
    }

    private void brancamentAdjacent() {
        for (int i = 0; i < code.size(); i++) {
            if (isIf(code.get(i).getInstructionType())) { //if cond goto e1
                //guardo la etiqueta
                Operator3Address e1 = code.get(i).getOperators()[2];
                int posIf = i;
                i++;
                if (code.get(i).getInstructionType() == InstructionType.GOTO && i < code.size()) { //goto e2
                    //guardamos la etiqueta del goto
                    Operator3Address e2 = code.get(i).getOperators()[2];
                    int posGoto = i;
                    i++;
                    if (code.get(i).getInstructionType() == InstructionType.SKIP && code.get(i).getOperators()[2].getLabel().equals(e1.getLabel())&& i < code.size()) { //e1 : skip
                        
                        code.get(posIf).setInstructionType(negCond(code.get(posIf).getInstructionType()));
                        code.get(posIf).setOperator(2, e2);
                        code.remove(posGoto);
                        //miramos si podemos borrar el e1:skip
                        boolean borrar = true;
                        for (int j = 0; j < code.size(); j++) {
                            if(code.get(j).getInstructionType() == InstructionType.GOTO && code.get(j).getOperators()[2].getLabel().equals(e1.getLabel())){
                                borrar = false;
                                break;
                            }
                        }
                        if(borrar){
                            //el skip e1 ahora estará en la posición del goto porque como hemos borrado el goto, todas las
                            //instrucciones por delante del goto han retrocedido una posición.
                            code.remove(posGoto);
                        }
                    }
                }
            }
        }
    }
    
    private void brancamentSobreBrancament(){
        for (int i = 0; i < code.size(); i++) {
            if(isIf(code.get(i).getInstructionType())){
                int posIf = i;
                Operator3Address e1 = code.get(i).getOperators()[2];
                for (int j = i + 1; j < code.size(); j++) {
                    if(code.get(j).getInstructionType() == InstructionType.SKIP && code.get(j).getOperators()[2].getLabel().equals(e1.getLabel())){
                        if(j + 1 < code.size() && code.get(j + 1).getInstructionType() == InstructionType.GOTO){
                            code.get(posIf).setOperator(2, code.get(j + 1).getOperators()[2]);
                            boolean borrar = true;
                            for (int k = 0; k < code.size(); k++) {
                                if(code.get(k).getInstructionType() == InstructionType.GOTO && code.get(k).getOperators()[2].getLabel().endsWith(e1.getLabel())){
                                    borrar = false;
                                    break;
                                }
                            }
                            if(borrar){
                                code.remove(j);
                            }
                        }
                    }
                }
            }
        }
    }
    
    //assignacions booleanes ja optimitzades en el propi analisiSemantic
    
    private void operacioConstant1(){
        for (int i = 0; i < code.size(); i++) {
            if(isOp(code.get(i).getInstructionType()) && code.get(i).getOperators()[0].getType() == Type.literal &&
                    code.get(i).getOperators()[1].getType() == Type.literal){
                int a = (int)code.get(i).getOperators()[0].getLiteral();
                int b = (int)code.get(i).getOperators()[1].getLiteral();
                switch(code.get(i).getInstructionType()){
                    case ADD: a = a + b;
                    break;
                    case DIV: a = a / b;
                    break;
                    case MOD: a = a % b;
                    break;
                    case MUL: a = a * b;
                    break;
                    case SUB: a = a - b;
                    break;      
                }
                code.get(i).setInstructionType(InstructionType.CLONE);
                code.get(i).setOperator(0, new Operator3Address(a,CastType.INT));
                code.get(i).setOperator(1, null);
            }
        }
    }
    
    private void operacioConstant2(){
        for (int i = 0; i < code.size(); i++) {
            if(isIf(code.get(i).getInstructionType()) && code.get(i).getOperators()[0].getType() == Type.literal &&
                    code.get(i).getOperators()[1].getType() == Type.literal){
                boolean res = false;
                switch(code.get(i).getOperators()[0].getCastType()){ 
                    case INT: 
                        int a = (int)code.get(i).getOperators()[0].getLiteral();
                        int b = (int)code.get(i).getOperators()[1].getLiteral();
                        switch(code.get(i).getInstructionType()){
                            case IFEQ: if(a == b){
                                res = true;
                            }
                            break;
                            case IFGE: if(a >= b){
                                res = true;
                            }
                            break;
                            case IFGT: if(a > b){
                                res = true;
                            }
                            break;
                            case IFLE: if(a <= b){
                                res = true;
                            }
                            break;
                            case IFLT: if(a < b){
                                res = true;
                            }
                            break;
                            case IFNE: if(a != b){
                                res = true;
                            }
                            break;
                        }
                    break;
                    case CHAR:
                        char c = (char)code.get(i).getOperators()[0].getLiteral();
                        char d = (char)code.get(i).getOperators()[1].getLiteral();
                        switch(code.get(i).getInstructionType()){
                            case IFEQ: if(c == d){
                                res = true;
                            }
                            break;
                            case IFGE: if(c >= d){
                                res = true;
                            }
                            break;
                            case IFGT: if(c > d){
                                res = true;
                            }
                            break;
                            case IFLE: if(c <= d){
                                res = true;
                            }
                            break;
                            case IFLT: if(c < d){
                                res = true;
                            }
                            break;
                            case IFNE: if(c != d){
                                res = true;
                            }
                            break;
                        }
                    break;
                    case BOOL:
                        boolean e = (boolean)code.get(i).getOperators()[0].getLiteral();
                        boolean f = (boolean)code.get(i).getOperators()[1].getLiteral();
                        switch(code.get(i).getInstructionType()){
                            case IFEQ: if(e == f){
                                res = true;
                            }
                            break;
                            case IFNE: if(e != f){
                                res = true;
                            }
                            break;
                        }
                    break;
                    case STRING:
                        String g = (String)code.get(i).getOperators()[0].getLiteral();
                        String h = (String)code.get(i).getOperators()[1].getLiteral();
                        switch(code.get(i).getInstructionType()){
                            case IFEQ: if(g.equals(h)){
                                res = true;
                            }
                            break;
                            case IFNE: if(!g.equals(h)){
                                res = true;
                            }
                            break;
                        }
                    break;
                }
                if(res){
                    code.get(i).setInstructionType(InstructionType.GOTO);
                    code.get(i).setOperator(0, null);
                    code.get(i).setOperator(1, null);
                }else{
                    code.remove(i);
                }
            }
        }
    }
    
    private void codiInaccesible1(){
        for (int i = 0; i < code.size(); i++) {
            //borrar goto innecesarios
            if(code.get(i).getInstructionType() == InstructionType.GOTO
                && i + 1 < code.size() && code.get(i + 1).getInstructionType() == InstructionType.GOTO){
                code.remove(i + 1);
            }
        }
    }
    
    private void codiInaccesible2(){
        for (int i = 0; i < code.size(); i++) {
            if(code.get(i).getInstructionType() == InstructionType.GOTO){
                String label = code.get(i).getOperators()[2].getLabel();
                for (int j = i + 1; j < code.size(); j++) {
                    if(code.get(j).getInstructionType() == InstructionType.SKIP){
                        if(code.get(j).getOperators()[2].getLabel().equals(label)){
                            //borrar codigo desde el goto e1 hasta skip e1
                            for (int k = i + 1; k < j; k++) {
                                code.remove(i + 1);
                            }
                            break;
                        }else{
                            //mirar si tiene un goto en el codigo
                            String aux = code.get(j).getOperators()[2].getLabel();
                            boolean trobat = false;
                            for (int k = 0; k < code.size(); k++) {
                                if(code.get(k).getInstructionType() == InstructionType.GOTO
                                   && code.get(k).getOperators()[2].getLabel().equals(aux)){
                                    trobat = true;
                                    break;
                                }
                            }
                            if(trobat){
                                break;
                            }
                        }                     
                    }
                }
            }
        }
    }
    
    private boolean isIf(InstructionType inst) {
        return inst == InstructionType.IFEQ || inst == InstructionType.IFGE || inst == InstructionType.IFGT
                || inst == InstructionType.IFLE || inst == InstructionType.IFLT || inst == InstructionType.IFNE;
    }

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

    private boolean isOp(InstructionType inst) {
        return inst == InstructionType.ADD || inst == InstructionType.DIV || inst == InstructionType.MOD
                || inst == InstructionType.MUL || inst == InstructionType.SUB;
    }
}
