package expression.exceptions;

public class InvalidArgumentException extends EvaluateException {
    public InvalidArgumentException() {
        super("The argument is not in the range of valid values of the function");
    }
}
