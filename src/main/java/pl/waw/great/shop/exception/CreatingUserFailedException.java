package pl.waw.great.shop.exception;

public class CreatingUserFailedException extends RuntimeException {
    private final String name;

    public CreatingUserFailedException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
