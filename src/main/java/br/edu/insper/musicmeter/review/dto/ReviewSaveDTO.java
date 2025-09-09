package br.edu.insper.musicmeter.review.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

import java.util.Set;

public record ReviewSaveDTO(
    User reviewer,
    int rating,
    String text
) {

    public static ReviewSaveDTO from(Review review) {
        return new ReviewSaveDTO(
                review.getReviewer(),
                review.getRating(),
                review.getText()
        );
    }

    public static Review to(ReviewSaveDTO review) {
        return new Review(
                review.reviewer,
                review.rating,
                review.text
        );
    }
}
