package com.cinema.administration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, NoSuchElementException.class})
    public ResponseEntity<HttpStatusCode> handleUnexpectedException(Exception exception) {
        log.error("Exception: {}", exception.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpStatusCode> handleIllegalArgumentException(Exception exception) {
        log.error("Exception: {}", exception.getMessage());
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
