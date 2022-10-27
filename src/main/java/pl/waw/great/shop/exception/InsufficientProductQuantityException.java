package pl.waw.great.shop.exception;

public class InsufficientProductQuantityException extends RuntimeException {

    private final String productTitle;
    private final Long quantity;


    public InsufficientProductQuantityException(String productTitle, Long quantity) {
        this.productTitle = productTitle;
        this.quantity = quantity;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public Long getQuantity() {
        return quantity;
    }
}
