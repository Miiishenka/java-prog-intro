package expression;

import expression.exceptions.OverflowException;

import java.util.Objects;

import static java.lang.Math.abs;

abstract public class UnaryOperation implements Operand {
    protected final Operand operand;
    private final int hashCode;

    protected UnaryOperation(Operand operand) {
        this.operand = operand;
        this.hashCode = Objects.hash(operand, getTag());
    }
    abstract String getTag();
    public abstract int evaluate(int x) throws OverflowException;
    public int evaluate(int x, int y, int z) {
        return evaluate(operand.evaluate(x, y, z));
    }

    public String toString() {
        return getTag() + "(" + operand + ")";
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
        UnaryOperation operation = (UnaryOperation) obj;
        return operand.equals(operation);
    }
    public String toMiniString() {
        if (abs(operand.getPriority()) > 1) {
            return getTag() + "(" + operand.toMiniString() + ")";
        } else {
            return getTag() + " " + operand.toMiniString();
        }
    }
}
