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
        String tag = rightOperand != null ? getTag(priority) : "";
        skipWhitespace();
        Operand leftOperand = switch (priority) {
            case 4, 3 -> parseBinaryOperation(null, priority - 1);
            case 2 -> {
                if (rightOperand == null || !tag.isEmpty()) {
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
                    if (tag.equals("set")) {
                        return parseBinaryOperation(new Set(rightOperand, leftOperand), 4);
                    }
                    if (tag.equals("clear")) {
                        return parseBinaryOperation(new Clear(rightOperand, leftOperand), 4);
                    }
                    take();
                    return rightOperand;
                }
                case 3 -> {
                    if (tag.equals("+")) {
                        return parseBinaryOperation(new Add(rightOperand, leftOperand), 3);
                    }
                    if (tag.equals("-")) {
                        return parseBinaryOperation(new Subtract(rightOperand, leftOperand), 3);
                    }
                    return rightOperand;
                }
                case 2 -> {
                    if (tag.equals("*")) {
                        return parseBinaryOperation(new Multiply(rightOperand, leftOperand), 2);
                    }
                    if (tag.equals("/")) {
                        return parseBinaryOperation(new Divide(rightOperand, leftOperand), 2);
                    }
                    return rightOperand;
                }
            }
        }
        return null;
    }

    String getTag(int priority) {
        return switch (priority) {
            case 4 -> buildToken();
            case 3 -> {
                if (test('-') || test('+')) {
                    yield Character.toString(take());
                } else {
                    yield "";
                }
            }
            case 2 -> {
                if (test('/') || test('*')) {
                    yield Character.toString(take());
                } else {
                    yield "";
                }
            }
            default -> "";
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
        if (take('(')) {
            return parseBinaryOperation(null, 4);
        }
        String tag = buildToken();
        if (tag.equals("count")) {
            return new Count(parseUnaryOperation());
        }
        if (tag.equals("x") || tag.equals("y") || tag.equals("z")) {
            return new Variable(tag);
        }
        takePrev(tag.length());
        return null;
    }
}