package com.gmail.derevets.artem.autoparkservice.exception;

public class CloudApplicationException extends RuntimeException {
    public CloudApplicationException() {
        super();
    }

    public CloudApplicationException(String message) {
        super(message);
    }

    public CloudApplicationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CloudApplicationException(Throwable throwable) {
        super(throwable);
    }
}
