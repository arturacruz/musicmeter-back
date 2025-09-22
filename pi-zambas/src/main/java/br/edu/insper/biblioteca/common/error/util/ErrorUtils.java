package br.edu.insper.biblioteca.common.error.util;

import br.edu.insper.biblioteca.common.error.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.*;

public class ErrorUtils {
    public static ResponseEntity<ErrorDTO> throwNotFound(RuntimeException err) {
        ErrorDTO errorDTO = new ErrorDTO(
                err,
                NOT_FOUND
        );
        return ResponseEntity.status(NOT_FOUND).body(errorDTO);
    }

    public static ResponseEntity<ErrorDTO> throwBadRequest(RuntimeException err) {
        ErrorDTO errorDTO = new ErrorDTO(
                err,
                BAD_REQUEST
        );
        return ResponseEntity.status(BAD_REQUEST).body(errorDTO);
    }

    public static ResponseEntity<ErrorDTO> throwConflict(RuntimeException err) {
        ErrorDTO errorDTO = new ErrorDTO(
                err,
                CONFLICT
        );
        return ResponseEntity.status(CONFLICT).body(errorDTO);
    }
}
