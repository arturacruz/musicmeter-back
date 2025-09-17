package br.edu.insper.musicmeter.music.controller;

import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.music.dto.MusicDTO;
import br.edu.insper.musicmeter.music.service.MusicService;
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
class MusicControllerTest {

    @InjectMocks
    private MusicController controller;

    @Mock
    private MusicService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_getMusics() throws Exception {
        Music music = new Music(1, "Song A", "Artist A", null, 4);

        Mockito.when(service.getMusics()).thenReturn(List.of(music));

        mockMvc.perform(MockMvcRequestBuilders.get("/music"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Song A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Artist A"));
    }

    @Test
    void test_postMusic() throws Exception {
        MusicDTO dto = new MusicDTO(0, "Song B", "Artist B", null, 5);
        Music music = MusicDTO.to(dto);

        Mockito.when(service.saveMusic(Mockito.any())).thenReturn(music);

        mockMvc.perform(MockMvcRequestBuilders.post("/music")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"Song B\",\"artist\":\"Artist B\",\"album\":null,\"rating\":5}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Song B"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Artist B"));
    }
}
