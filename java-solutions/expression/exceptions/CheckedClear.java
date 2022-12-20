package expression.exceptions;

import expression.Clear;
import expression.Operand;

public class CheckedClear extends Clear {
    public CheckedClear(Operand firstOperand, Operand operand) {
        super(firstOperand, operand);
    }
    public int binaryEvaluate(int left, int right) {
        return left & ~(1 << right);
    }
}
