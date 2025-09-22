package br.edu.insper.biblioteca.livro.controller;

import br.edu.insper.biblioteca.livro.Livro;
import br.edu.insper.biblioteca.livro.dto.LivroSaveDTO;
import br.edu.insper.biblioteca.livro.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public List<Livro> getLivros() {
        return service.getLivros();
    }

    @GetMapping("/{id}")
    public Livro getLivro(@PathVariable Integer id) {
        return service.getLivro(id);
    }

    @PostMapping
    public Livro postLivro(@Valid @RequestBody LivroSaveDTO dto) {
        return service.addLivro(dto);
    }

    @DeleteMapping("/{id}")
    public Livro deleteLivro(@PathVariable Integer id) {
        return service.deleteLivro(id);
    }
}
