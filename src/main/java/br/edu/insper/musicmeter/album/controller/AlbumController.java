package br.edu.insper.musicmeter.album.controller;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.dto.AlbumDTO;
import br.edu.insper.musicmeter.album.service.AlbumService;
import br.edu.insper.musicmeter.common.spotify.SpotifyRequester;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @GetMapping("/search/{query}")
    public JsonNode search(@PathVariable String query) {
        return SpotifyRequester.searchAlbum(query);
    }

    @GetMapping("/{id}")
    public JsonNode getAlbum(@PathVariable Integer id) {
        Album album = service.getAlbum(id);
        JsonNode ret = SpotifyRequester.getAlbum(album.getSpotifyId());
        ((ObjectNode) ret).put("rating", album.getRating());
        return ret;
    }
}