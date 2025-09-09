package br.edu.insper.musicmeter.review.dto;

import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

import java.util.Set;

public record ReviewDTO(
        int id,
        User reviewer,
        int rating,
        String text
) {

    public static ReviewDTO from(Review review) {
        return new ReviewDTO(
            review.getId(),
            review.getReviewer(),
            review.getRating(),
            review.getText()
        );
    }

    public static Review to(ReviewDTO review) {
        return new Review(
            review.id,
            review.reviewer,
            review.rating,
            review.text
        );
    }
}
