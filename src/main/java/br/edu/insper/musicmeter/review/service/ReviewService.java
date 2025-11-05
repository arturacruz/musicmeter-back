package br.edu.insper.musicmeter.review.service;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.dto.AlbumDTO;
import br.edu.insper.musicmeter.album.service.AlbumService;
import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.review.dto.ReviewDTO;
import br.edu.insper.musicmeter.review.dto.ReviewSaveDTO;
import br.edu.insper.musicmeter.review.repository.ReviewRepository;
import br.edu.insper.musicmeter.user.User;
import br.edu.insper.musicmeter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

    public List<ReviewDTO> getReviews() {
        return reviewRepository.findAll().stream().map(ReviewDTO::from).toList();
    }

    public ReviewDTO saveReview(ReviewSaveDTO review) {
        User user = userService.getUser(review.userId());
        Album album;
        try {
            album = albumService.getAlbumBySpotifyId(review.albumId());
        } catch (ObjectNotFoundException e) {
            album = albumService.saveAlbum(AlbumDTO.from(new Album(review.albumId(), review.rating())));
        }
        Review rev = review.to(user, album);
        reviewRepository.save(rev);
        return ReviewDTO.from(rev);
    }

    public ReviewDTO updateReview(int id, ReviewSaveDTO review) throws ObjectNotFoundException {
        Review model = getReview(id);
        User user = userService.getUser(review.userId());
        Album album = albumService.getAlbumBySpotifyId(review.albumId());
        Review updated = review.to(user, album);
        updated.setId(model.getId());
        return saveReview(ReviewSaveDTO.from(updated));
    }

    public ReviewDTO deleteReview(int id) throws ObjectNotFoundException {
        Review review = getReview(id);
        reviewRepository.deleteById(id);
        return ReviewDTO.from(review);
    }

    public Review getReview(Integer id) throws ObjectNotFoundException {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty()) {
            throw new ObjectNotFoundException("");
        }
        return review.get();
    }
}
