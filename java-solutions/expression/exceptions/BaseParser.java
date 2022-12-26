package expression.exceptions;


public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }
    protected BaseParser() {
        this.source = null;
    }
    protected char getCh() {
        return ch;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }
    protected char takePrev() {
        ch = source.hasPrev() ? source.prev() : END;
        return ch;
    }
    protected void takePrev(int count) {
        for (int i = 0; i < count; i++) {
            takePrev();
        }
    }

    protected String buildToken() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetter(getCh()) || Character.isDigit(getCh())) {
            sb.append(take());
        }
        return sb.toString();
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}