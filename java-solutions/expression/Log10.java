package expression;

import expression.exceptions.OverflowException;

public class Log10 extends UnaryOperation {
    public Log10(Operand operand) {
        super(operand);
    }
    @Override
    public String getTag() {
        return "log10";
    }

    @Override
    public int evaluate(int x) throws OverflowException {
        int result = 0;
        while (x > 9) {
            x /= 10;
            result += 1;
        }
        return result;
    }
    public double evaluate(double x) {
        int result = 0;
        while (x > 9) {
            x /= 10;
            result += 1;
        }
        return result;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
