package br.edu.insper.biblioteca.livro.controller;

import br.edu.insper.biblioteca.common.exception.IncorrectFieldUsageException;
import br.edu.insper.biblioteca.common.exception.ResourceNotFoundException;
import br.edu.insper.biblioteca.livro.Livro;
import br.edu.insper.biblioteca.livro.dto.LivroSaveDTO;
import br.edu.insper.biblioteca.livro.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {
    
    @InjectMocks
    private LivroController controller;

    @Mock
    private LivroService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_getLivros() throws Exception {
        Livro livro = new Livro(1, "livro daora", "fantasia", "jorge", 10, LocalDate.now());

        Mockito.when(service.getLivros()).thenReturn(List.of(livro));

        mockMvc.perform(MockMvcRequestBuilders.get("/livro"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("livro daora"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genero").value("fantasia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].autor").value("jorge"));
    }

    @Test
    void test_getLivro() throws Exception {
        Livro livro = new Livro(1, "livro daora", "fantasia", "jorge", 10, LocalDate.now());
        Mockito.when(service.getLivro(1)).thenReturn(livro);


        mockMvc.perform(MockMvcRequestBuilders.get("/livro/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("livro daora"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genero").value("fantasia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autor").value("jorge"));
    }

    @Test
    void test_addLivro() throws Exception {
        LivroSaveDTO dto = new LivroSaveDTO("livro daora", "fantasia", "jorge", 1000);
        Livro livro = dto.to();
        
        Mockito.when(service.addLivro(Mockito.any())).thenReturn(livro);

        mockMvc.perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"livro daora\",\"genero\":\"fantasia\",\"autor\":\"jorge\",\"anoPublicacao\":1000}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("livro daora"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genero").value("fantasia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autor").value("jorge"));

    }

}
