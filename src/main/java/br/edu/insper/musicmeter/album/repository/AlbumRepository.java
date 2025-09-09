package br.edu.insper.musicmeter.album.repository;

import br.edu.insper.musicmeter.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}

