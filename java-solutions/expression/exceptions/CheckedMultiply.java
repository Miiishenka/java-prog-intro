package expression.exceptions;

import expression.Multiply;
import expression.Operand;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }
    @Override
    public int binaryEvaluate(int left, int right) {
        if (left > 0 && right > 0 && left > Integer.MAX_VALUE / right) {
            throw new OverflowException();
        }
        if (left > 0 && right < 0 && right < Integer.MIN_VALUE / left) {
            throw new OverflowException();
        }
        if (left < 0 && right > 0 && left < Integer.MIN_VALUE / right) {
            throw new OverflowException();
        }
        if (left < 0 && right < 0 && left < Integer.MAX_VALUE / right) {
            throw new OverflowException();
        }
        return left * right;
    }
}
