package br.edu.insper.musicmeter.review;

import br.edu.insper.musicmeter.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private User reviewer;
    private int rating;
    private String text;

    public Review(User reviewer, int rating, String text) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.text = text;
    }
}