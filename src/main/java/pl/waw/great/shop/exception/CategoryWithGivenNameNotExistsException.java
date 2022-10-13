package pl.waw.great.shop.exception;


public class CategoryWithGivenNameNotExistsException extends RuntimeException {
    private final String name;

    public CategoryWithGivenNameNotExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
