package expression;

import java.util.Objects;

public class Const implements Operand {
    protected final Number cnst;
    protected final int hashCode;
    public Const(int cnst) {
        this.cnst = cnst;
        this.hashCode = Objects.hash(cnst);
    }

    public Const(double cnst) {
        this.cnst = cnst;
        this.hashCode = Objects.hash(cnst);
    }
    @Override
    public int evaluate(int x) {
        return cnst.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return cnst.intValue();
    }

    @Override
    public double evaluate(double x) {
        return cnst.doubleValue();
    }

    @Override
    public String toString() {
        return cnst.toString();
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
        return cnst.toString();
    }
}
