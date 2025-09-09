package br.edu.insper.musicmeter.music.controller;

import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.music.dto.MusicDTO;
import br.edu.insper.musicmeter.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController
{
    @Autowired
    private MusicService service;

    @GetMapping
    public List<Music> getMusics() {
        return service.getMusics();
    }

    @GetMapping("/{id}")
    public Music getMusics(@PathVariable Integer id) {
        return service.getMusic(id);
    }

    @PostMapping
    public Music postMusic(@RequestBody MusicDTO musicDTO) {
        return service.saveMusic(musicDTO);
    }

    @DeleteMapping("/{id}")
    public Music deleteMusic(@PathVariable Integer id) {
        return service.deleteMusic(id);
    }

    @PutMapping("/{id}")
    public Music putMusic(@PathVariable Integer id, @RequestBody MusicDTO musicDTO) {
        return service.saveMusic(musicDTO);
    }
}