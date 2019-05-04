package com.gmail.derevets.artem.autoparkservice.exception.transport;

import com.gmail.derevets.artem.autoparkservice.exception.CloudApplicationException;

public class TransportException extends CloudApplicationException {
    public TransportException() {
        super();
    }

    public TransportException(String message) {
        super(message);
    }

    public TransportException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TransportException(Throwable throwable) {
        super(throwable);
    }
}
