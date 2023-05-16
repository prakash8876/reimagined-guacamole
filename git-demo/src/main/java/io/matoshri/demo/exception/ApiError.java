package io.matoshri.demo.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private String path;
    private HttpStatus code;
}
