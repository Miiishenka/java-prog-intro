package expression;

import expression.exceptions.OverflowException;

public class Negate extends UnaryOperation {
    public Negate(Operand operand) {
        super(operand);
    }
    @Override
    public String getTag() {
        return "-";
    }

    @Override
    public int evaluate(int x) throws OverflowException {
        return -x;
    }
    public double evaluate(double x) {
        return -x;
    }

    @Override
    public int getPriority() {
        return -1;
    }
}
