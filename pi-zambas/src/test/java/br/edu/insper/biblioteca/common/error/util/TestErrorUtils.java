package br.edu.insper.biblioteca.common.error.util;

import br.edu.insper.biblioteca.common.error.dto.ErrorDTO;
import br.edu.insper.biblioteca.common.exception.IncorrectFieldUsageException;
import br.edu.insper.biblioteca.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestErrorUtils {
    @Test
    void test_notFound() {
        ResponseEntity<ErrorDTO> dto = ErrorUtils.throwNotFound(new ResourceNotFoundException(0, ""));
        assert(dto.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

}
