package br.edu.insper.biblioteca.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final String message;
    public ResourceNotFoundException(int id, String object)
    {
        this.message = object + " with id " + id + " not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
