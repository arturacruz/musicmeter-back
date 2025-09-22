package br.edu.insper.biblioteca.common.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class IncorrectFieldUsageException extends RuntimeException
{
    private final String message;
    public IncorrectFieldUsageException(MethodArgumentNotValidException err)
    {
        Map<String, String> errors = new HashMap<>();
        err.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        this.message = "The following fields have errors: " + errors;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
