package br.edu.insper.musicmeter.music.controller;

import br.edu.insper.musicmeter.common.spotify.SpotifyRequester;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
public class MusicController
{
    @GetMapping("/search/{query}")
    public JsonNode search(@PathVariable String query) {
        return SpotifyRequester.searchMusic(query);
    }
}