package com.gmail.derevets.artem.autoparkservice.exception.route;

import com.gmail.derevets.artem.autoparkservice.exception.CloudApplicationException;
import org.springframework.stereotype.Component;

@Component
public class RouteException extends CloudApplicationException {
    public RouteException() {
        super();
    }

    public RouteException(String message) {
        super(message);
    }

    public RouteException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RouteException(Throwable throwable) {
        super(throwable);
    }
}
