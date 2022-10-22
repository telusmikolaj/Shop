package pl.waw.great.shop.exception;

public class ProductWithGivenTitleNotExistsException extends RuntimeException {

    private final String title;

    public ProductWithGivenTitleNotExistsException(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
