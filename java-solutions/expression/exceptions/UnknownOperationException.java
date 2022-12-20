package expression.exceptions;

public class UnknownOperationException extends ParseException {

    public UnknownOperationException(String message) {
        super(message);
    }
}
