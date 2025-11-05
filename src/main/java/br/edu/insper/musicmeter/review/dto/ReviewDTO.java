package br.edu.insper.musicmeter.review.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

public record ReviewDTO(
        int id,
        int userId,
        int rating,
        String text,
        String albumId
) {

    public static ReviewDTO from(Review review) {
        return new ReviewDTO(
            review.getId(),
            review.getReviewer().getId(),
            review.getRating(),
            review.getText(),
            review.getAlbum().getSpotifyId()
        );
    }

    public Review to(User user, Album album) {
        return new Review(
                id,
                user,
                rating,
                text,
                album
        );
    }
}
