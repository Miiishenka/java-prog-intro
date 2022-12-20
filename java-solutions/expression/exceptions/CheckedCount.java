package expression.exceptions;

import expression.Count;
import expression.Operand;

public class CheckedCount extends Count {

    public CheckedCount(Operand operand) {
        super(operand);
    }
    @Override
    public int evaluate(int x) {
        return Integer.bitCount(x);
    }
}
