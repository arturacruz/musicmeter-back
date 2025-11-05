package br.edu.insper.musicmeter.review.repository;

import br.edu.insper.musicmeter.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByAlbumSpotifyId(String spotifyId);
}

