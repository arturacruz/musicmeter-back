package org.example.livro.service;

import org.example.common.exception.ResourceNotFoundException;
import org.example.livro.Livro;
import org.example.livro.dto.LivroSaveDTO;
import org.example.livro.repository.LivroRepository;
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
