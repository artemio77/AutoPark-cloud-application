package com.gmail.derevets.artem.autoparkservice.exception.route;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouteNotFoundException extends RouteException {
    public RouteNotFoundException() {
        super();
    }

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RouteNotFoundException(Throwable throwable) {
        super(throwable);
    }

}
