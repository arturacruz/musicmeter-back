package br.edu.insper.musicmeter.review.repository;

import br.edu.insper.musicmeter.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

