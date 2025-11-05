package br.edu.insper.musicmeter.common;

import br.edu.insper.musicmeter.common.exception.IncorrectFieldUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ErrorUtils
{
    public static ResponseEntity<ErrorDTO> throwNotFound(RuntimeException err)
    {
        ErrorDTO errorDTO = new ErrorDTO(
                err,
                NOT_FOUND
        );
        return ResponseEntity.status(NOT_FOUND).body(errorDTO);
    }

    public static ResponseEntity<ErrorDTO> throwBadRequest(RuntimeException err)
    {
        ErrorDTO errorDTO = new ErrorDTO(
                err,
                BAD_REQUEST
        );
        return ResponseEntity.status(BAD_REQUEST).body(errorDTO);
    }

    public static ResponseEntity<ErrorDTO> throwIncorrectFieldUsage(MethodArgumentNotValidException err)
    {
        IncorrectFieldUsageException ex = new IncorrectFieldUsageException(err);
        ErrorDTO errorDTO = new ErrorDTO(
                ex,
                BAD_REQUEST
        );
        return ResponseEntity.status(BAD_REQUEST).body(errorDTO);
    }
}