package nl.novi.TechItEasy.Exceptions;

public class RecordTooLongException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public RecordTooLongException() {
        super();
    }
    public RecordTooLongException(String message) {
        super(message);
    }
}
