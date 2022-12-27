package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements TripleParser {

    public CustomExpression parse(String inputString) {
//        System.out.println("Input: " + inputString);
        parse(new StringSource(inputString));
        final CustomExpression result = parseLevel0();
        if (eof()) {
//            System.out.println("Correct: " + result.toString());
            return result;
        }
//        System.out.println("Stopped at: " + curChar());
//        System.out.println("With result: " + result.toString());
        throw error("End of string expected");
    }

    private CustomExpression parseLevel0() { // clear, set
        skipWhitespace();
        CustomExpression left = parseLevel1();
        skipWhitespace();

        while (test('c') || test('s')) {
            if (take('c')) {
                expect("lear");
                left = new Clear(left, parseLevel1());
            } else if (take('s')) {
                expect("et");
                left = new Set(left, parseLevel1());
            }
            skipWhitespace();
        }
        return left;
    }

    private CustomExpression parseLevel1() { // +, -
        skipWhitespace();
        CustomExpression left = parseLevel2();
        skipWhitespace();
        while (test('+') || test('-')) {
            if (take('+')) {
                left = new Add(left, parseLevel2());
            } else if (take('-')) {
                left = new Subtract(left, parseLevel2());
            }
            skipWhitespace();
        }
        return left;
    }

    private CustomExpression parseLevel2() { // *, /
        skipWhitespace();
        CustomExpression left = parseLevel3();
        skipWhitespace();
        while (test('*') || test('/')) {
            if (take('*')) {
                left = new Multiply(left, parseLevel3());
            } else if (take('/')) {
                left = new Divide(left, parseLevel3());
            }
            skipWhitespace();
        }
        return left;
    }

    private CustomExpression parseLevel3() { // -, const, var, count, (, )
        skipWhitespace();
        if (take('(')) {
            CustomExpression res = parseLevel0();
            expect(')');
            return res;
        }
        if (take('-')) {
            if (!between('0', '9')) {
                CustomExpression res = parseLevel3();
                return new UnMinus(res);
            }
            back();
        }
        if (take('c')) {
            expect("ount");
            CustomExpression res = parseLevel3();
            return new Count(res);
        }
        if (take('x')) {
            return new Variable("x");
        }
        if (take('y')) {
            return new Variable("y");
        }
        if (take('z')) {
            return new Variable("z");
        }
        final StringBuilder sb = new StringBuilder();
        takeInteger(sb);
        skipWhitespace();
        return new Const(Integer.parseInt(sb.toString()));
    }

    private void takeDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(take());
        }
    }

    private void takeInteger(final StringBuilder sb) {
        if (take('-')) {
            sb.append('-');
        }
        if (take('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            takeDigits(sb);
        } else {
            throw error("Invalid number");
        }
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(curChar())) {
            take();
        }
    }
}
