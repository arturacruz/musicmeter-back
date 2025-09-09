package br.edu.insper.musicmeter.user.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

import java.util.HashSet;
import java.util.Set;

public record UserDTO(
    int id,
    String name,
    String displayName,
    Set<Review> reviews,
    Set<Album> albums,
    Music favoriteSong
) {

    public static UserDTO from(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getDisplayName(),
            user.getReviews(),
            user.getFavoriteAlbums(),
            user.getFavoriteSong()
        );
    }

    public static User to(UserDTO user) {
        return new User(
                user.id(),
                user.name(),
                user.displayName(),
                user.reviews(),
                user.albums(),
                user.favoriteSong()
        );
    }
}
