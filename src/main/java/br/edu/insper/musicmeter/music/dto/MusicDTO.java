package br.edu.insper.musicmeter.music.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.music.Music;

public record MusicDTO(
        int id,
        String name,
        String artist,
        Album album,
        int rating
) {

    public static MusicDTO from(Music music) {
        return new MusicDTO(
                music.getId(),
                music.getTitle(),
                music.getArtist(),
                music.getAlbum(),
                music.getRating()
        );
    }

    public static Music to(MusicDTO music) {
        return new Music(
                music.id,
                music.name,
                music.artist,
                music.album,
                music.rating
        );
    }
}
