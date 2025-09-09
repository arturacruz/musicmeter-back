package br.edu.insper.musicmeter.album.controller;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.dto.AlbumDTO;
import br.edu.insper.musicmeter.album.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController
{
    @Autowired
    private AlbumService service;

    @GetMapping
    public List<Album> getAlbums() {
        return service.getAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbums(@PathVariable Integer id) {
        return service.getAlbum(id);
    }

    @PostMapping
    public Album postAlbum(@RequestBody AlbumDTO albumDTO) {
        return service.saveAlbum(albumDTO);
    }

    @DeleteMapping("/{id}")
    public Album deleteAlbum(@PathVariable Integer id) {
        return service.deleteAlbum(id);
    }

    @PutMapping("/{id}")
    public Album putAlbum(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        return service.saveAlbum(albumDTO);
    }
}