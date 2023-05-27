package io.matoshri.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiGlobalExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex,
                                                                HttpServletRequest request) {
        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex,
                                                                HttpServletRequest request) {
        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(value = {UserExistsException.class})
    public ResponseEntity<ApiError> handleUserExistsException(UserExistsException ex,
                                                                HttpServletRequest request) {
        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.ok(error);
    }
}
