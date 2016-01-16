package com.blog.core.services.exceptions;

public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException() {
        super();
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

}