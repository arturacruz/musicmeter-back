package br.edu.insper.musicmeter.artist.controller;

import br.edu.insper.musicmeter.common.spotify.SpotifyRequester;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
public class ArtistController
{
    @GetMapping("/search/{query}")
    public JsonNode search(@PathVariable String query) {
        return SpotifyRequester.searchArtist(query);
    }
}