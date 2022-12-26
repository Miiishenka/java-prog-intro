package expression.exceptions;

import expression.Log10;
import expression.Operand;

public class CheckedLog10 extends Log10 {
    public CheckedLog10(Operand operand) {
        super(operand);
    }
    @Override
    public int evaluate(int x) {
        if (x < 1) {
            throw new InvalidArgumentException();
        }
        int result = 0;
        while (x > 9) {
            x /= 10;
            result += 1;
        }
        return result;
    }
}
