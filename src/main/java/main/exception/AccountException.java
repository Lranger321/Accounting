package main.exception;

public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Object... args) {
        super(String.format(message, args));
    }
}
