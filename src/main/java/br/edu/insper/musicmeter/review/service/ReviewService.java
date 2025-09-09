package br.edu.insper.musicmeter.review.service;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.review.dto.ReviewSaveDTO;
import br.edu.insper.musicmeter.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(ReviewSaveDTO review) {
        reviewRepository.save(ReviewSaveDTO.to(review));
        return ReviewSaveDTO.to(review);
    }

    public Review updateReview(int id, ReviewSaveDTO review) throws ObjectNotFoundException {
        Review model = getReview(id);
        Review updated = ReviewSaveDTO.to(review);
        updated.setId(model.getId());
        saveReview(ReviewSaveDTO.from(updated));
        return updated;
    }

    public Review deleteReview(int id) throws ObjectNotFoundException {
        Review review = getReview(id);
        reviewRepository.deleteById(id);
        return review;
    }

    public Review getReview(Integer id) throws ObjectNotFoundException {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty()) {
            throw new ObjectNotFoundException("");
        }
        return review.get();
    }
}
