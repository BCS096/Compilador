package compilador.sintactic.nodes;

public class ElemListNode extends BaseNode {

    private ElemListNode elemList;
    private ElemIdAssigNode elemIdAssig;

    public ElemListNode(ElemListNode elemList, ElemIdAssigNode elemIdAssig, int line, int column) {
        super("ELEM_LIST", false, line, column);
        this.elemList = elemList;
        this.elemIdAssig = elemIdAssig;
    }

    public ElemListNode getElemList() {
        return this.elemList;
    }

    public ElemIdAssigNode getElemIdAssig() {
        return this.elemIdAssig;
    }
}
