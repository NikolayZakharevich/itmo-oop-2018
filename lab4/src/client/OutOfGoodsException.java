package client;

public class OutOfGoodsException extends RuntimeException {
    public OutOfGoodsException() {
        super();
    }

    public OutOfGoodsException(String message) {
        super(message);
    }
}
