public class IllegalAccountOperationException extends RuntimeException {
    public IllegalAccountOperationException(String message) {
        super(message);
    }

    public IllegalAccountOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}