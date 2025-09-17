package br.edu.insper.musicmeter.review.service;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.review.repository.ReviewRepository;
import br.edu.insper.musicmeter.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    private ReviewService service;

    @Mock
    private ReviewRepository repository;

    private Review review;

    @BeforeEach
    void setup() {
        User user = new User();
        user.setId(1);
        user.setName("Reviewer");

        review = new Review(1, user, 5, "Awesome");
    }

    @Test
    void test_getReviews() {
        Mockito.when(repository.findAll()).thenReturn(List.of(review));

        List<Review> result = service.getReviews();

        assertEquals(1, result.size());
        assertEquals("Awesome", result.get(0).getText());
    }

    @Test
    void test_getReview_found() throws Exception {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(review));

        Review result = service.getReview(1);

        assertEquals("Awesome", result.getText());
    }

    @Test
    void test_getReview_notFound() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.getReview(1));
    }
}
