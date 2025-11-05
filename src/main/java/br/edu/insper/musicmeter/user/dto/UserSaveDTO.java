package br.edu.insper.musicmeter.user.dto;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.User;

import java.util.Set;

public record UserSaveDTO(
    String name,
    String displayName
) {

    public static UserSaveDTO from(User user) {
        return new UserSaveDTO(
            user.getName(),
            user.getDisplayName()
        );
    }

    public static User to(UserSaveDTO user) {
        return new User(
                user.name(),
                user.displayName()
        );
    }
}
