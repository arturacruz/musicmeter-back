package br.edu.insper.musicmeter.album.dto;

import br.edu.insper.musicmeter.album.Album;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public record AlbumDTO(
        @NonNull @NotEmpty  String spotifyId
) {

    public static AlbumDTO from(Album album) {
        return new AlbumDTO(
                album.getSpotifyId()
        );
    }

    public Album to() {
        return new Album(
                spotifyId
        );
    }
}
