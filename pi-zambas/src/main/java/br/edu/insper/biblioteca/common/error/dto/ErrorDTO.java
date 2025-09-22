package br.edu.insper.biblioteca.common.error.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDTO {
    private final String exception;
    private final String message;
    private final HttpStatus statusCode;
    private final LocalDateTime timestamp;

    public ErrorDTO(RuntimeException ex, HttpStatus statusCode) {
        this.exception = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}