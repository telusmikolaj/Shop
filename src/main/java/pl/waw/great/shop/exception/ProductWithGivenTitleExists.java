package pl.waw.great.shop.exception;

public class ProductWithGivenTitleExists extends RuntimeException {
    private final String title;

    public ProductWithGivenTitleExists(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
