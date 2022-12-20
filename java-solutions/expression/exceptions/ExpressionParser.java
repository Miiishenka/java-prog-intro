package expression.exceptions;

import expression.*;

public final class ExpressionParser extends BaseParser implements TripleParser {
    private int bracketsCount = 0;
    public ExpressionParser(CharSource source) {
        super(source);
    }
    public ExpressionParser() {
        super();
    }

    @Override
    public TripleExpression parse(final String expression) throws ParseException {
        return parse(new StringSource(expression));
    }
    public TripleExpression parse(final CharSource source) throws ParseException {
        return new ExpressionParser(source).parseExpression();
    }

    private TripleExpression parseExpression() throws ParseException {
        final Operand expression = parseBinaryOperation(null, 4);
        skipWhitespace();
        if (expression == null) {
            throw new InvalidExpressionException("This is not an expression");
        }
        if (bracketsCount > 0) {
            throw new BracketsCountException("No closing parenthesis");
        }
        if (bracketsCount < 0) {
            throw new BracketsCountException("No opening parenthesis");
        }
        return expression;

    }

    private void skipWhitespace() {
        while(Character.isWhitespace(getCh())) {
            take();
        }
    }
    private Operand parseBinaryOperation(Operand firstOperand, int priority) throws ParseException {
        skipWhitespace();
        char tag = firstOperand != null ? getTag(priority) : '\0';
        skipWhitespace();
        Operand operand = switch (priority) {
            case 4, 3 -> parseBinaryOperation(null, priority - 1);
            case 2 -> {
                if (firstOperand == null || tag != '\0') {
                    yield parseUnaryOperation();
                } else {
                    yield null;
                }
            }
            default -> null;
        };
        if (firstOperand == null && operand != null) {
            return parseBinaryOperation(operand, priority);
        }
        if (operand != null) {
            return switch (tag) {
                case 's' -> parseBinaryOperation(new CheckedSet(firstOperand, operand), priority);
                case 'c' -> parseBinaryOperation(new CheckedClear(firstOperand, operand), priority);
                case '+' -> parseBinaryOperation(new CheckedAdd(firstOperand, operand), priority);
                case '-' -> parseBinaryOperation(new CheckedSubtract(firstOperand, operand), priority);
                case '*' -> parseBinaryOperation(new CheckedMultiply(firstOperand, operand), priority);
                case '/' -> parseBinaryOperation(new CheckedDivide(firstOperand, operand), priority);
                default -> throw new UnknownOperationException("Spaces in numbers");
            };
        }
        if (tag != '\0') {
            throw new InvalidExpressionException("No last argument");
        }
        if (priority == 4 && take(')')) {
            bracketsCount--;
        }
        return firstOperand;
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
    private Operand parseUnaryOperation() throws ParseException {
        skipWhitespace();
        char minus = test('-') ? take() : '\0';
        if (between('0', '9')) {
            StringBuilder sb = minus == '-' ? new StringBuilder().append('-') : new StringBuilder();
            while (between('0', '9')) {
                sb.append(take());
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new InvalidConstException("Invalid const, " + sb + " is not int");
            }

        }
        if (minus == '-') {
            Operand operand = parseUnaryOperation();
            if (operand == null) {
                throw new InvalidExpressionException("Bare -");
            }
            return new CheckedNegate(operand);
        }
        if (take('c')) {
            if (take('o')) {
                expect("unt");
                return new CheckedCount(parseUnaryOperation());
            }
            takePrev();
        }
        if (test('x') || test('y') || test('z')) {
            return new Variable(Character.toString(take()));
        }
        if (take('(')) {
            bracketsCount++;
            return parseBinaryOperation(null, 4);
        }
        if (eof() || test(')')) {
            return null;
        }
        throw new UnknownOperationException("Invalid operation");
    }
}