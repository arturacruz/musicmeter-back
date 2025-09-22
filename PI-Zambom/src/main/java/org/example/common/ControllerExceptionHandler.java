package org.example.common;

import org.example.common.error.dto.ErrorDTO;
import org.example.common.error.util.ErrorUtils;
import org.example.common.exception.IncorrectFieldUsageException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    protected ResponseEntity<ErrorDTO> notFound(RuntimeException err) {
        return ErrorUtils.throwNotFound(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> incorrectFieldUsage(MethodArgumentNotValidException err) {
        return ErrorUtils.throwBadRequest(new IncorrectFieldUsageException(err));
    }
}