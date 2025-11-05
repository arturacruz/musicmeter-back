package br.edu.insper.musicmeter.review.controller;

import br.edu.insper.musicmeter.review.dto.ReviewDTO;
import br.edu.insper.musicmeter.review.dto.ReviewSaveDTO;
import br.edu.insper.musicmeter.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController
{
    @Autowired
    private ReviewService service;

    @GetMapping
    public List<ReviewDTO> getReviews() {
        return service.getReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO getReviews(@PathVariable Integer id) {
        return ReviewDTO.from(service.getReview(id));
    }

    @GetMapping("/album/{id}")
    public List<ReviewDTO> getReviewsForAlbum(@PathVariable String id) {
        return service.getAllReviewsInAlbum(id);
    }

    @PostMapping
    public ReviewDTO postReview(@RequestBody ReviewSaveDTO reviewDTO) {
        return service.saveReview(reviewDTO);
    }

    @DeleteMapping("/{id}")
    public ReviewDTO deleteReview(@PathVariable Integer id) {
        return service.deleteReview(id);
    }

    @PutMapping("/{id}")
    public ReviewDTO putReview(@PathVariable Integer id, @RequestBody ReviewSaveDTO reviewDTO) {
        return service.saveReview(reviewDTO);
    }
}