package sintactic.nodes;

public class MethodNode extends BaseNode {

    private ProcNode proc;
    private FuncNode func;

    public MethodNode(ProcNode proc, FuncNode func, int line, int column) {
        super("METHOD", false, line, column);
        this.proc = proc;
        this.func = func;
    }

    public ProcNode getProc() {
        return proc;
    }

    public FuncNode getFunc() {
        return func;
    }

}
