package nl.novi.TechItEasy.Exceptions;

public class RecordAssignedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RecordAssignedException() {
        super();
    }
    public RecordAssignedException(String message) {
        super(message);
    }
}
