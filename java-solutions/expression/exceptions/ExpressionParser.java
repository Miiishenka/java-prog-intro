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
        String tag = firstOperand != null ? getTag(priority) : "";
        skipWhitespace();
        Operand operand = switch (priority) {
            case 4, 3 -> parseBinaryOperation(null, priority - 1);
            case 2 -> {
                if (firstOperand == null || !tag.isEmpty()) {
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
                case "set" -> parseBinaryOperation(new CheckedSet(firstOperand, operand), priority);
                case "clear" -> parseBinaryOperation(new CheckedClear(firstOperand, operand), priority);
                case "+" -> parseBinaryOperation(new CheckedAdd(firstOperand, operand), priority);
                case "-" -> parseBinaryOperation(new CheckedSubtract(firstOperand, operand), priority);
                case "*" -> parseBinaryOperation(new CheckedMultiply(firstOperand, operand), priority);
                case "/" -> parseBinaryOperation(new CheckedDivide(firstOperand, operand), priority);
                default -> throw new UnknownOperationException("Spaces in numbers");
            };
        }
        if (!tag.isEmpty()) {
            throw new InvalidExpressionException("No last argument");
        }
        if (priority == 4 && take(')')) {
            bracketsCount--;
        }
        return firstOperand;
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
        if (take('(')) {
            bracketsCount++;
            return parseBinaryOperation(null, 4);
        }
        if (eof() || test(')')) {
            return null;
        }
        String tag = buildToken();
        if (tag.equals("count")) {
            Operand operand = parseUnaryOperation();
            if (operand == null) {
                throw new InvalidExpressionException("Bare count");
            }
            return new CheckedCount(operand);
        }
        if (tag.equals("pow10")) {
            Operand operand = parseUnaryOperation();
            if (operand == null) {
                throw new InvalidExpressionException("Bare pow10");
            }
            return new CheckedPow10(operand);
        }
        if (tag.equals("log10")) {
            Operand operand = parseUnaryOperation();
            if (operand == null) {
                throw new InvalidExpressionException("Bare log10");
            }
            return new CheckedLog10(operand);
        }
        if (tag.equals("x") || tag.equals("y") || tag.equals("z")) {
            return new Variable(tag);
        }
        if (tag.equals("clear") || tag.equals("set")) {
            try {
                takePrev(tag.length());
                return null;
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidExpressionException("bare set/clear");
            }
        }
        throw new UnknownOperationException("Invalid operation");
    }
}