package expression;

import expression.exceptions.OverflowException;

public class Pow10 extends UnaryOperation {
    public Pow10(Operand operand) {
        super(operand);
    }
    @Override
    public String getTag() {
        return "pow10";
    }

    @Override
    public int evaluate(int x) throws OverflowException {
        int result = 1;
        for (int i = 0; i < x; i++) {
            result *= 10;
        }
        return result;
    }
    public double evaluate(double x) {
        int result = 1;
        for (int i = 0; i < x; i++) {
            result *= 10;
        }
        return result;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
