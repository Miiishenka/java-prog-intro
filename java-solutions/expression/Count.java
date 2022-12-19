package expression;

public class Count extends UnaryOperation {
    public Count(Operand operand) {
        super(operand);
    }

    @Override
    String getTag() {
        return "count";
    }

    @Override
    public int evaluate(int x) {
        return Integer.bitCount(x);
    }

    @Override
    public int getPriority() {
        return -5;
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }
}
