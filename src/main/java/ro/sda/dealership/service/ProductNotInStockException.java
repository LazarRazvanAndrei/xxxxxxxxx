package ro.sda.dealership.service;

public class ProductNotInStockException extends RuntimeException {
    public ProductNotInStockException() {
    }

    public ProductNotInStockException(String message) {
        super(message);
    }

    public ProductNotInStockException(Throwable cause) {
        super(cause);
    }
}
