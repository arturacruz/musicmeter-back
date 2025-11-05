package br.edu.insper.musicmeter.user.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserDTO(
    int id,
    String name,
    String displayName,
    Set<Integer> reviews,
    Set<Integer> albums
) {

    public static UserDTO from(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getDisplayName(),
            user.getReviews().stream().map(Review::getId).collect(Collectors.toSet()),
            user.getFavoriteAlbums().stream().map(Album::getId).collect(Collectors.toSet())
        );
    }

    public User to(Set<Review> review, Set<Album> album) {
        return new User(
                id,
                name,
                displayName,
                review,
                album
        );
    }
}
