package expression.exceptions;

import expression.Operand;
import expression.Pow10;

public class CheckedPow10 extends Pow10 {
    public CheckedPow10(Operand operand) {
        super(operand);
    }
    @Override
    public int evaluate(int x) {
        if (x > 9) {
            throw new OverflowException();
        }
        if (x < 0) {
            throw new InvalidArgumentException();
        }
        int result = 1;
        for (int i = 0; i < x; i++) {
            result *= 10;
        }
        return result;
    }
}
