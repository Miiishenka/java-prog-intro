package expression.parser;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }
    public boolean hasPrev() {
        return pos > 0;
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }
    public char prev() {
        pos--;
        return data.charAt(pos - 1);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }
}
