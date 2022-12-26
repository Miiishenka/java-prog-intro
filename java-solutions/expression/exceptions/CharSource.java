package expression.exceptions;

public interface CharSource {
    boolean hasNext();
    boolean hasPrev();
    char next();
    char prev();
    IllegalArgumentException error(String message);
}
