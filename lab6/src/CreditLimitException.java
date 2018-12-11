public class CreditLimitException extends RuntimeException {
    public CreditLimitException() {
        super();
    }

    public CreditLimitException(String message) {
        super(message);
    }

    public CreditLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
