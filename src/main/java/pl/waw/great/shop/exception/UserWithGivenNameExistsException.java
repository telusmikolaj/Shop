package pl.waw.great.shop.exception;

public class UserWithGivenNameExistsException extends RuntimeException {

    private final String name;

    public UserWithGivenNameExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
