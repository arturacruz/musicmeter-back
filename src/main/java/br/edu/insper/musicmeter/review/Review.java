package br.edu.insper.musicmeter.review;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User reviewer;
    private int rating;
    private String text;
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Review(User reviewer, int rating, String text, Album album) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.text = text;
        this.album = album;
    }
}