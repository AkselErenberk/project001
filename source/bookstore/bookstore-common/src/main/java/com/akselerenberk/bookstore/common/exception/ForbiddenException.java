package com.akselerenberk.bookstore.common.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable throwable) {
        super(throwable);
    }

    public ForbiddenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
