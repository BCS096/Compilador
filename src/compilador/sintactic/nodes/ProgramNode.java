package sintactic.nodes;

public class ProgramNode extends BaseNode {

    private DeclListNode declList;
    private MethodListNode methodList;
    private MainNode main;

    public ProgramNode(DeclListNode declList, MethodListNode methodList, MainNode main, int line, int column) {
        super("PROGRAM", false, line, column);
        this.declList = declList;
        this.methodList = methodList;
        this.main = main;
    }

    public DeclListNode getDeclList() {
        return this.declList;
    }

    public MethodListNode getMethodList() {
        return this.methodList;
    }

    public MainNode getMain() {
        return this.main;
    }
}
