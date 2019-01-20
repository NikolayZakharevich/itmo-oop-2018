package dao;

public class DatabaseObjectNotFoundException extends RuntimeException {
    public DatabaseObjectNotFoundException() {
        super();
    }

    public DatabaseObjectNotFoundException(String message) {
        super(message);
    }
}
