package com.gmail.derevets.artem.autoparkservice.exception.transport;

public class TransportNotFoundException extends TransportException {

    public TransportNotFoundException() {
        super();
    }

    public TransportNotFoundException(String message) {
        super(message);
    }

    public TransportNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TransportNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
