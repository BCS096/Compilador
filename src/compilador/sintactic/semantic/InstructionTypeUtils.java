package sintactic.semantic;

import sintactic.RelOpType;

public class InstructionTypeUtils {
    public static InstructionType getRelationalIf(RelOpType relType) {
        switch(relType){
            case EQ:
                return InstructionType.IFEQ;
            case NEQ:
                return InstructionType.IFNE;
            case LT:
                return InstructionType.IFLT;
            case GT:
                return InstructionType.IFGT;
            case LE:
                return InstructionType.IFLE;
            case GE:
                return InstructionType.IFGE;
            default:
                throw new AssertionError(relType.name());
        }
    }
}
