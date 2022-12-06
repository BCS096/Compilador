
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

/**
 *
 * @author tomeu
 */
public class InitTupelNode extends BaseNode{
    private ParamInNode params;
    
    public InitTupelNode (ParamInNode params, int line, int column){
        super("INIT_TUPEL", false, line, column);
        this.params = params;
    }

    public ParamInNode getParams() {
        return params;
    }
}
