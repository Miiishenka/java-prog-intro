package expression;

public class Multiply extends BinaryOperation {
    public Multiply(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    String getTag() {
        return "*";
    }

    @Override
    public int binaryEvaluate(int left, int right) {
        return left * right;
    }

    @Override
    double binaryEvaluate(double left, double right) {
        return left * right;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
