package br.edu.insper.musicmeter.album.dto;

import br.edu.insper.musicmeter.album.Album;

public record AlbumDTO(
        String spotifyId
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
