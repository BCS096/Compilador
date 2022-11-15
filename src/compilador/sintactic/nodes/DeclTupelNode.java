/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador.sintactic.nodes;

/**
 *
 * @author tomeu
 */
public class DeclTupelNode extends BaseNode {
    
    private IdentifierNode id;
    private ParamListNode params;
    private TupelDeclNode tupeldecl;
    
    public DeclTupelNode(IdentifierNode id, ParamListNode params, TupelDeclNode tupeldecl, int line, int column){
        super("DECL_TUPEL",false,line,column);
        this.id = id;
        this.params = params;
        this.tupeldecl = tupeldecl;
    }

    public IdentifierNode getId() {
        return id;
    }

    public ParamListNode getParams() {
        return params;
    }

    public TupelDeclNode getTupeldecl() {
        return tupeldecl;
    }
}
