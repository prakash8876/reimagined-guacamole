package com.example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class AppGlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleResourceNoFoundException(ResourceNotFoundException exception,
                                                                   HttpServletRequest request) {
      AppError error = new AppError(exception.getMessage(),
              request.getRequestURI(),
              HttpStatus.NOT_FOUND,
              LocalDateTime.now());
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppError> handleRuntimeException(RuntimeException exception,
                                                           WebRequest request) {
        AppError error = new AppError(exception.getMessage(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }
}
