package br.edu.insper.biblioteca.livro.service;

import br.edu.insper.biblioteca.common.exception.ResourceNotFoundException;
import br.edu.insper.biblioteca.livro.Livro;
import br.edu.insper.biblioteca.livro.controller.LivroController;
import br.edu.insper.biblioteca.livro.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @InjectMocks
    private LivroService service;

    @Mock
    private LivroRepository repository;

    private Livro livro;
    @BeforeEach
    void setup() {
        livro = new Livro(1, "livro daora", "fantasia", "jorge", 1000, LocalDate.now());
    }

    @Test
    void test_getLivros() {
        Mockito.when(repository.findAll()).thenReturn(List.of(livro));

        List<Livro> result = service.getLivros();

        assertEquals(1, result.size());
        assertEquals("livro daora", result.getFirst().getTitulo());
    }

    @Test
    void test_getLivro_found() throws Exception {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(livro));

        Livro result = service.getLivro(1);

        assertEquals("jorge", result.getAutor());
    }

    @Test
    void test_getAlbum_notFound() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getLivro(1));
    }

    @Test
    void test_deleteAlbum() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(livro));

        Livro result = service.deleteLivro(1);

        assertEquals("jorge", result.getAutor());
    }

}
