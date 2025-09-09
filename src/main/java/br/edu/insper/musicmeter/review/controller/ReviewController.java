package br.edu.insper.musicmeter.review.controller;

import br.edu.insper.musicmeter.review.Review;
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
    public List<Review> getReviews() {
        return service.getReviews();
    }

    @GetMapping("/{id}")
    public Review getReviews(@PathVariable Integer id) {
        return service.getReview(id);
    }

    @PostMapping
    public Review postReview(@RequestBody ReviewSaveDTO reviewDTO) {
        return service.saveReview(reviewDTO);
    }

    @DeleteMapping("/{id}")
    public Review deleteReview(@PathVariable Integer id) {
        return service.deleteReview(id);
    }

    @PutMapping("/{id}")
    public Review putReview(@PathVariable Integer id, @RequestBody ReviewSaveDTO reviewDTO) {
        return service.saveReview(reviewDTO);
    }
}