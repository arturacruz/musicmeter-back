package br.edu.insper.musicmeter.user;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.user.exception.FavoriteAlbumListFullException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User
{

    private int id;
    private String name;
    private String displayName;
    /*private Set<User> friends;*/
    private Set<Review> reviews = new HashSet<>();

    private Set<Album> favoriteAlbums = new HashSet<>();

    private Music favoriteSong;

    public User(String name, String displayName, Set<Review> reviews, Set<Album> favoriteAlbums, Music favoriteSong) {
        this.name = name;
        this.displayName = displayName;
        this.reviews = reviews;
        this.favoriteAlbums = favoriteAlbums;
        this.favoriteSong = favoriteSong;
    }

    public void addFavoriteAlbum(Album album) throws FavoriteAlbumListFullException {
        if(favoriteAlbums.size() >= 4) {
            throw new FavoriteAlbumListFullException("TODO", HttpStatus.BAD_REQUEST);
        }
        favoriteAlbums.add(album);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }


}