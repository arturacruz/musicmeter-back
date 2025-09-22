package br.edu.insper.biblioteca.livro.dto;

import br.edu.insper.biblioteca.livro.Livro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroSaveDTO(
        @NotBlank(message = "Titulo cannot be blank.")
        @NotNull(message = "Titulo cannot be null.")
        String titulo,
       
        @NotBlank(message = "Genero cannot be blank.")
        @NotNull(message = "Genero cannot be null.")
        String genero,

        @NotBlank(message = "Autor cannot be blank.")
        @NotNull(message = "Autor cannot be null.")
        String autor,

        @Max(value = 2025, message = "Ano de publicação must be in the past.")
        int anoPublicacao
) {

    public Livro to() {
        return new Livro(
                titulo, genero, autor, anoPublicacao
        );
    }
}
