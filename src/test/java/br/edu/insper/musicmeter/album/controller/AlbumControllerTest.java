package br.edu.insper.musicmeter.album.controller;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.dto.AlbumDTO;
import br.edu.insper.musicmeter.album.service.AlbumService;
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

import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AlbumControllerTest {

    @InjectMocks
    private AlbumController controller;

    @Mock
    private AlbumService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_getAlbums() throws Exception {
        Album album = new Album(1, "Album Test", "Artist Test", new HashSet<>(), 5);

        Mockito.when(service.getAlbums()).thenReturn(List.of(album));

        mockMvc.perform(MockMvcRequestBuilders.get("/album"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Album Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Artist Test"));
    }

    @Test
    void test_postAlbum() throws Exception {
        AlbumDTO dto = new AlbumDTO(0, "New Album", "New Artist", new HashSet<>(), 5);
        Album album = AlbumDTO.to(dto);

        Mockito.when(service.saveAlbum(Mockito.any())).thenReturn(album);

        mockMvc.perform(MockMvcRequestBuilders.post("/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"New Album\",\"artist\":\"New Artist\",\"musics\":[],\"rating\":5}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("New Artist"));
    }
}
