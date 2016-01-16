package com.blog.core.services.exceptions;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
        super();
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
