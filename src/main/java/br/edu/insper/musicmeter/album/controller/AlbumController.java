package br.edu.insper.musicmeter.album.controller;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.dto.AlbumDTO;
import br.edu.insper.musicmeter.album.service.AlbumService;
import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.common.spotify.SpotifyRequester;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.review.dto.ReviewDTO;
import br.edu.insper.musicmeter.review.service.ReviewService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController
{
    @Autowired
    private AlbumService service;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Album> getAlbums() {
        return service.getAlbums();
    }

    @GetMapping("/search/{query}")
    public JsonNode search(@PathVariable String query) {
        return SpotifyRequester.searchAlbum(query);
    }

    @GetMapping("/{id}")
    public JsonNode getAlbum(@PathVariable String id) {
        JsonNode ret = SpotifyRequester.getAlbum(id);
        try {
            service.getAlbumBySpotifyId(id);
        } catch (ObjectNotFoundException e) {
            return ret;
        }

        List<ReviewDTO> albumReviews = reviewService.getAllReviewsInAlbum(id);

        int rating = albumReviews.stream().mapToInt(ReviewDTO::rating).sum() / albumReviews.size();

        ((ObjectNode) ret).put("rating", rating);
        return ret;
    }
}