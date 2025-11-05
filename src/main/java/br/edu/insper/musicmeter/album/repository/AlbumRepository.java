package br.edu.insper.musicmeter.album.repository;

import br.edu.insper.musicmeter.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> findBySpotifyId(String spotifyId);
}

