package expression;

import static java.lang.Math.abs;
abstract public class Operation implements Operand {
    protected final Operand leftOperand;
    protected final Operand rightOperand;
    private final int hashCode;

    protected Operation(Operand leftOperand, Operand rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.hashCode = (25432 * leftOperand.hashCode() + 31635 * getTag().hashCode()
                + rightOperand.hashCode() * 78911) % Integer.MAX_VALUE;
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
        Operation operation = (Operation) obj;
        return leftOperand.equals(operation.leftOperand) && rightOperand.equals(operation.rightOperand);
    }
    public String toMiniString() {
        String s = "";
        if (abs(leftOperand.getPriority()) > abs(getPriority()) + 1) {
            s += "(" + leftOperand.toMiniString() + ")";
        } else {
            s += leftOperand.toMiniString();
        }
        s += " " + getTag() + " ";
        if ((abs(abs(rightOperand.getPriority()) - abs(getPriority())) < 2 && getPriority() < 0)
                || abs(rightOperand.getPriority()) > abs(getPriority())) {
            s += "(" + rightOperand.toMiniString() + ")";
        } else {
            s += rightOperand.toMiniString();
        }
        return s;

    }
}
