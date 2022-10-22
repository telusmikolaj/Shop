package pl.waw.great.shop.exception;

public class InvalidCommentIndexException extends RuntimeException {
    private final int index;

    public InvalidCommentIndexException(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
