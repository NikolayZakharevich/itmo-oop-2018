public class IllegalAccountAccessException extends RuntimeException {
    public IllegalAccountAccessException(String message) {
        super(message);
    }

    public IllegalAccountAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
