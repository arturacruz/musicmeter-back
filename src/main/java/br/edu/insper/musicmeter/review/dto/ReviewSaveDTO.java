package br.edu.insper.musicmeter.review.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public record ReviewSaveDTO(
        @NonNull int userId,
        @NonNull int rating,
        @NonNull @NotEmpty String text,
        @NonNull @NotEmpty String albumId
) {

    public static ReviewSaveDTO from(Review review) {
        return new ReviewSaveDTO(
                review.getReviewer().getId(),
                review.getRating(),
                review.getText(),
                review.getAlbum().getSpotifyId()
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
