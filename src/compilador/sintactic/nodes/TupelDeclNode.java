/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

/**
 *
 * @author tomeu
 */
public class TupelDeclNode extends BaseNode {
    private InitTupelNode init;
    
    public TupelDeclNode (InitTupelNode init, int line, int column){
        super("TUPEL_DECL", false, line, column);
        this.init = init;
    }

    public InitTupelNode getInit() {
        return init;
    }
}
