package expression.exceptions;

import expression.Operand;
import expression.Set;

public class CheckedSet extends Set {
    public CheckedSet(Operand firstOperand, Operand operand) {
        super(firstOperand, operand);
    }
    @Override
    public int binaryEvaluate(int left, int right) {
        return left | (1 << right);
    }
}
