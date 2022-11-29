package expression;

public class Variable implements Operand {
    protected final String var;
    protected final int hashCode;
    public Variable(String var) {
        this.var = var;
        this.hashCode = var.hashCode();
    }
    @Override
    public int evaluate(int x) {
        return x;
    }
    public int evaluate(int x, int y, int z) {
        if (var.equals("x")) {
            return x;
        }
        if (var.equals("y")) {
            return y;
        }
        if (var.equals("z")) {
            return z;
        }
        throw new AssertionError();
    }
    @Override
    public String toString() {
        return var;
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
        return hashCode() == obj.hashCode();
    }

    @Override
    public int getPriority() {
        return 0;
    }
    @Override
    public String toMiniString() {
        return var;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }
}
