package service;

public class NoConnectionException extends RuntimeException {
    public NoConnectionException() {
        super();
    }

    public NoConnectionException(String message) {
        super(message);
    }
}
