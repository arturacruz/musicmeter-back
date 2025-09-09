package br.edu.insper.musicmeter.music.service;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.music.dto.MusicDTO;
import br.edu.insper.musicmeter.music.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public List<Music> getMusics() {
        return musicRepository.findAll();
    }

    public Music saveMusic(MusicDTO music) {
        musicRepository.save(MusicDTO.to(music));
        return MusicDTO.to(music);
    }

    public Music updateMusic(int id, MusicDTO music) throws ObjectNotFoundException {
        Music model = getMusic(id);
        Music updated = MusicDTO.to(music);
        updated.setId(model.getId());
        saveMusic(MusicDTO.from(updated));
        return updated;
    }

    public Music deleteMusic(int id) throws ObjectNotFoundException {
        Music music = getMusic(id);
        musicRepository.deleteById(id);
        return music;
    }

    public Music getMusic(Integer id) throws ObjectNotFoundException {
        Optional<Music> music = musicRepository.findById(id);
        if(music.isEmpty()) {
            throw new ObjectNotFoundException("");
        }
        return music.get();
    }
}
