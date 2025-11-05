package br.edu.insper.musicmeter.review.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

public record ReviewSaveDTO(
        int userId,
        int rating,
        String text,
        int albumId
) {

    public static ReviewSaveDTO from(Review review) {
        return new ReviewSaveDTO(
                review.getReviewer().getId(),
                review.getRating(),
                review.getText(),
                review.getAlbum().getId()
        );
    }

    public Review to(User user, Album album) {
        return new Review(
                user,
                rating,
                text,
                album
        );
    }
}
