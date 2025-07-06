package com.BrunoFujisaki.devbooks_backend.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationDataErrors>> handleMethodArgumentNoValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors.stream()
                        .map(ValidationDataErrors::new)
                        .toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex) {
        var msg = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity
                .status(msg.status)
                .body(msg);
    }

    @ExceptionHandler(CategoriaException.class)
    public ResponseEntity<RestErrorMessage> handleUnprocessableException(RuntimeException ex) {
        var msg = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return ResponseEntity
                .status(msg.status)
                .body(msg);
    }

    private record RestErrorMessage(HttpStatus status, String message) {}

    private record ValidationDataErrors(
            String field,
            String message
    ) {
        private ValidationDataErrors(FieldError fe) {
            this(fe.getField(), fe.getDefaultMessage());
        }
    }
}
