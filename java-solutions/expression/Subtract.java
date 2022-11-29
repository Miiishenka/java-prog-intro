package expression;

public class Subtract extends Operation {
    public Subtract(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    String getTag() {
        return "-";
    }

    @Override
    int binaryEvaluate(int left, int right) {
        return left - right;
    }

    @Override
    double binaryEvaluate(double left, double right) {
        return left - right;
    }

    @Override
    public int getPriority() {
        return -5;
    }
}
