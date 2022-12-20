package expression.exceptions;

import expression.Operand;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }
    @Override
    public int binaryEvaluate(int left, int right) {
        if (right <= 0 && left > Integer.MAX_VALUE + right
                || right >= 0 && left < Integer.MIN_VALUE + right) {
            throw new OverflowException();
        }
        return left - right;
    }
}
