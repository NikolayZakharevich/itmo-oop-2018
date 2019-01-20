package service;

public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException() {
        super();
    }

    public DuplicateObjectException(String message) {
        super(message);
    }
}
