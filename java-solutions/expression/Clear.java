package expression;

public class Clear extends BinaryOperation {
    public Clear(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    String getTag() {
        return "clear";
    }

    @Override
    public int binaryEvaluate(int left, int right) {
        return left & ~(1 << right);
    }

    @Override
    double binaryEvaluate(double left, double right) {
        return 0;
    }

    @Override
    public int getPriority() {
        return -7;
    }
}
