package com.example.app.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class AppError {
    private String error;
    private String path;
    private HttpStatus status;
    private LocalDateTime timestamp;

    public AppError() {}

    public AppError(String error, String path, HttpStatus status, LocalDateTime timestamp) {
        this.error = error;
        this.path = path;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
