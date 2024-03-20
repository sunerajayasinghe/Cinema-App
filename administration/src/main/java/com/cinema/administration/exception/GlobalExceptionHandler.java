package com.cinema.administration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String PRE_EXCEPTION_MESSAGE = "Exception: {}";

    @ExceptionHandler({Exception.class, NoSuchElementException.class})
    public ResponseEntity<HttpStatusCode> handleUnexpectedException(Exception exception) {
        log.error(PRE_EXCEPTION_MESSAGE, exception.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpStatusCode> handleIllegalArgumentException(Exception exception) {
        log.error(PRE_EXCEPTION_MESSAGE, exception.getMessage());
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpStatusCode> handleAuthenticationException(Exception exception) {
        log.error(PRE_EXCEPTION_MESSAGE, exception.getMessage());
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
