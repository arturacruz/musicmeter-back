package br.edu.insper.musicmeter.music.repository;

import br.edu.insper.musicmeter.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Integer> {
}

