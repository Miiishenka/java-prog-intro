package expression.exceptions;

import expression.Divide;
import expression.Operand;

public class CheckedDivide extends Divide {
    public CheckedDivide(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }
    @Override
    public int binaryEvaluate(int left, int right) {
        if (right == 0) {
            throw new DBZException();
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException();
        }
        return left / right;
    }

}
