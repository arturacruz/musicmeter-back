package br.edu.insper.musicmeter.user.exception;

import org.springframework.http.HttpStatus;

public class FavoriteAlbumListFullException extends RuntimeException
{
    private final HttpStatus status;
    public FavoriteAlbumListFullException(String message, HttpStatus status)
    {
        super(message);
        this.status = status;
    }
}
