package expression.exceptions;

import expression.Operand;
import expression.Set;

public class CheckedSet extends Set {
    public CheckedSet(Operand firstOperand, Operand operand) {
        super(firstOperand, operand);
    }
    @Override
    public int binaryEvaluate(int left, int right) {
        if (right >= 32) {
            throw new OverflowException();
        }
        return left | (1 << right);
    }
}
