package expression.parser;

import expression.*;

public final class ExpressionParser extends BaseParser implements TripleParser {
    public ExpressionParser(CharSource source) {
        super(source);
    }
    public ExpressionParser() {
        super();
    }

    @Override
    public TripleExpression parse(final String expression) {
        return parse(new StringSource(expression));
    }
    public TripleExpression parse(final CharSource source) {
        return new ExpressionParser(source).parseBinaryOperation(null, 4);
    }

    private void skipWhitespace() {
        while(Character.isWhitespace(getCh())) {
            take();
        }
    }
    private Operand parseBinaryOperation(Operand rightOperand, int priority) {
        skipWhitespace();
        char tag = rightOperand != null ? getTag(priority) : '\0';
        skipWhitespace();
        Operand leftOperand = switch (priority) {
            case 4, 3 -> parseBinaryOperation(null, priority - 1);
            case 2 -> {
                if (rightOperand == null || tag != '\0') {
                    yield parseUnaryOperation();
                } else {
                    yield null;
                }
            }
            default -> null;
        };
        if (rightOperand == null && leftOperand != null) {
            return parseBinaryOperation(leftOperand, priority);
        } else {
            switch (priority) {
                case 4 -> {
                    return switch (tag) {
                        case 's' -> parseBinaryOperation(new Set(rightOperand, leftOperand), 4);
                        case 'c' -> parseBinaryOperation(new Clear(rightOperand, leftOperand), 4);
                        default -> {
                            take();
                            yield rightOperand;
                        }
                    };
                }
                case 3 -> {
                    return switch (tag) {
                        case '+' -> parseBinaryOperation(new Add(rightOperand, leftOperand), 3);
                        case '-' -> parseBinaryOperation(new Subtract(rightOperand, leftOperand), 3);
                        default -> rightOperand;
                    };
                }
                case 2 -> {
                    return switch (tag) {
                        case '*' -> parseBinaryOperation(new Multiply(rightOperand, leftOperand), 2);
                        case '/' -> parseBinaryOperation(new Divide(rightOperand, leftOperand), 2);
                        default -> rightOperand;
                    };
                }
            }
        }
        return null;
    }

    char getTag(int priority) {
        return switch (priority) {
            case 4 -> {
                if (take('s')) {
                    expect("et");
                    yield 's'; // set
                } else if (take('c')) {
                    expect("lear");
                    yield 'c'; // clear
                } else {
                    yield '\0';
                }
            }
            case 3 -> {
                if (test('-') || test('+')) {
                    yield take();
                } else {
                    yield '\0';
                }
            }
            case 2 -> {
                if (test('/') || test('*')) {
                    yield take();
                } else {
                    yield '\0';
                }
            }
            default -> '\0';
        };
    }
    private Operand parseUnaryOperation() {
        skipWhitespace();
        char minus = test('-') ? take() : '\0';
        if (between('0', '9')) {
            StringBuilder sb = minus == '-' ? new StringBuilder().append('-') : new StringBuilder();
            while (between('0', '9')) {
                sb.append(take());
            }
            return new Const(Integer.parseInt(sb.toString()));
        }
        if (minus == '-') {
            return new Negate(parseUnaryOperation());
        }
        if (take('c')) {
            if (take('o')) {
                expect("unt");
                return new Count(parseUnaryOperation());
            }
            takePrev();
        }
        if (test('x') || test('y') || test('z')) {
            return new Variable(Character.toString(take()));
        }
        if (take('(')) {
            return parseBinaryOperation(null, 4);
        }
        return null;
    }
}