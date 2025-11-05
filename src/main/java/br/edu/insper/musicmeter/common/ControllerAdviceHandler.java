package br.edu.insper.musicmeter.common;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceHandler
{
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(RuntimeException err)
    {
        return ErrorUtils.throwNotFound(err);
    }

    @ExceptionHandler(exception = {

    })
    public ResponseEntity<ErrorDTO> handleBadRequest(RuntimeException err)
    {
        return ErrorUtils.throwBadRequest(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> incorrectFieldUsage(MethodArgumentNotValidException err)
    {
        return ErrorUtils.throwIncorrectFieldUsage(err);
    }


}
