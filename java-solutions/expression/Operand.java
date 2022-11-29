package expression;

public interface Operand extends Expression, TripleExpression, DoubleExpression {
    int getPriority();
}
