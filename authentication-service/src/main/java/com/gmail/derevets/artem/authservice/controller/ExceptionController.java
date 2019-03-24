package com.gmail.derevets.artem.authservice.controller;

import com.gmail.derevets.artem.authservice.exception.UserNotFoundException;
import com.gmail.derevets.artem.authservice.model.exception.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class ExceptionController {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionEntity> handleMyException(UserNotFoundException exception,
                                                             HttpServletRequest request) {
        ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .details(exception.getCause().toString())
                .message(exception.getMessage())
                .requestUrl(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
