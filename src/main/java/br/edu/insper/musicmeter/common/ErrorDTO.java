package br.edu.insper.musicmeter.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorDTO
{
    private final String exception;
    private final String message;
    private final HttpStatus statusCode;
    private final LocalDateTime timestamp;

    public ErrorDTO(RuntimeException ex, HttpStatus statusCode)
    {
        this.exception = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}