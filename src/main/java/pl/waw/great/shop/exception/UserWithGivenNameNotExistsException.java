package pl.waw.great.shop.exception;

public class UserWithGivenNameNotExistsException extends RuntimeException {

    private final String name;

    public UserWithGivenNameNotExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
