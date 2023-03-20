package nl.novi.TechItEasy.Exceptions;

public class NotANumberException extends RuntimeException {
    public NotANumberException() {
        super();
    }
    public NotANumberException (String message) {
        super(message);
    }
}
