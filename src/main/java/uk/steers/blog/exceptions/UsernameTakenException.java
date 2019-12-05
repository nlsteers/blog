package uk.steers.blog.exceptions;

public class UsernameTakenException extends RuntimeException {

    public UsernameTakenException(String err) {
        super(err);
    }

}
