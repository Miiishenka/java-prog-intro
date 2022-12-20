package expression.exceptions;

import expression.Add;
import expression.Operand;

public class CheckedAdd extends Add {
    public CheckedAdd(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }
    @Override
    public int binaryEvaluate(int left, int right) {
        if (right >= 0 && left > Integer.MAX_VALUE - right ||
                right <= 0 && left < Integer.MIN_VALUE - right) {
            throw new OverflowException();
        }
        return left + right;
    }
}
