public class IllegalMethodAnnotatedExeption extends RuntimeException {

    public IllegalMethodAnnotatedExeption(String message) {
        super(message);
    }

    public IllegalMethodAnnotatedExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
