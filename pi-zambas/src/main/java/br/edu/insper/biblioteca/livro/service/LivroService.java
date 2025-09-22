package br.edu.insper.biblioteca.livro.service;

import br.edu.insper.biblioteca.common.exception.ResourceNotFoundException;
import br.edu.insper.biblioteca.livro.Livro;
import br.edu.insper.biblioteca.livro.dto.LivroSaveDTO;
import br.edu.insper.biblioteca.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    public List<Livro> getLivros() {
        return repository.findAll();
    }

    public Livro getLivro(int id) throws ResourceNotFoundException {
        Optional<Livro> livro = repository.findById(id);

        if(livro.isEmpty()) {
            throw new ResourceNotFoundException(id, "livro");
        }

        return livro.get();
    }

    public Livro deleteLivro(int id) throws ResourceNotFoundException {
        Livro livro = getLivro(id);
        repository.deleteById(id);
        return livro;
    }

    public Livro addLivro(LivroSaveDTO dto) {
        return repository.save(dto.to());
    }
}
