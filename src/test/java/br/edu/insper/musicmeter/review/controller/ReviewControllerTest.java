package br.edu.insper.musicmeter.review.controller;

import br.edu.insper.musicmeter.review.Review;
import br.edu.insper.musicmeter.review.dto.ReviewSaveDTO;
import br.edu.insper.musicmeter.review.service.ReviewService;
import br.edu.insper.musicmeter.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @InjectMocks
    private ReviewController controller;

    @Mock
    private ReviewService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_getReviews() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Tester");

        Review review = new Review(1, user, 5, "Great!");

        Mockito.when(service.getReviews()).thenReturn(List.of(review));

        mockMvc.perform(MockMvcRequestBuilders.get("/review"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].text").value("Great!"));
    }

    @Test
    void test_postReview() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Tester");

        ReviewSaveDTO dto = new ReviewSaveDTO(user, 4, "Nice");
        Review review = ReviewSaveDTO.to(dto);

        Mockito.when(service.saveReview(Mockito.any())).thenReturn(review);

        mockMvc.perform(MockMvcRequestBuilders.post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reviewer\":{\"id\":1,\"name\":\"Tester\"},\"rating\":4,\"text\":\"Nice\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("Nice"));
    }
}
