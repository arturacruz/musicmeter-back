package br.edu.insper.musicmeter.album.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.review.Review;

import java.util.HashSet;
import java.util.Set;

public record AlbumDTO(   
        int id,
        String name,
        String artist,
        Set<Music> musics,
        int rating
) {

    public static AlbumDTO from(Album album) {
        return new AlbumDTO(
                album.getId(),
                album.getTitle(),
                album.getArtist(),
                album.getMusics(),
                album.getRating()
        );
    }

    public static Album to(AlbumDTO album) {
        return new Album(
                album.id,
                album.name,
                album.artist,
                album.musics,
                album.rating
        );
    }
}
