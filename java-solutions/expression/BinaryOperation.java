package expression;

import java.util.Objects;

import static java.lang.Math.abs;
abstract public class BinaryOperation implements Operand {
    protected final Operand leftOperand;
    protected final Operand rightOperand;
    private final int hashCode;

    protected BinaryOperation(Operand leftOperand, Operand rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.hashCode = Objects.hash(leftOperand, getTag(), rightOperand);
    }
    abstract String getTag();
    abstract int binaryEvaluate(int left, int right);

    public int evaluate(int x) {
        return binaryEvaluate(leftOperand.evaluate(x), rightOperand.evaluate(x));
    }
    public int evaluate(int x, int y, int z) {
        return binaryEvaluate(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }
    abstract double binaryEvaluate(double left, double right);

    public double evaluate(double x) {
        return binaryEvaluate(leftOperand.evaluate(x), rightOperand.evaluate(x));
    }

    public String toString() {
        return "(" + leftOperand.toString() + " " + getTag() + " " + rightOperand.toString() + ")";
    }
    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BinaryOperation operation = (BinaryOperation) obj;
        return leftOperand.equals(operation.leftOperand) && rightOperand.equals(operation.rightOperand);
    }
    public String toMiniString() {
        String s = "";
        if (abs(leftOperand.getPriority()) < abs(getPriority()) + 2) {
            s += leftOperand.toMiniString();
        } else {
            s += "(" + leftOperand.toMiniString() + ")";
        }
        s += " " + getTag() + " ";
        if (abs(rightOperand.getPriority()) < abs(getPriority()) + 1
                && (abs(abs(rightOperand.getPriority()) - abs(getPriority())) > 1 || getPriority() > -1)) {
            s += rightOperand.toMiniString();
        } else {
            s += "(" + rightOperand.toMiniString() + ")";
        }
        return s;
    }
}
