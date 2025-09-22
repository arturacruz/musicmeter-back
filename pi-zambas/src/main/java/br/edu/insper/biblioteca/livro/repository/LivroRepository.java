package br.edu.insper.biblioteca.livro.repository;

import br.edu.insper.biblioteca.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
