package br.edu.insper.musicmeter.music.service;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.music.Music;
import br.edu.insper.musicmeter.music.repository.MusicRepository;
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
class MusicServiceTest {

    @InjectMocks
    private MusicService service;

    @Mock
    private MusicRepository repository;

    private Music music;

    @BeforeEach
    void setup() {
        music = new Music(1, "Song Test", "Artist Test", null, 5);
    }

    @Test
    void test_getMusics() {
        Mockito.when(repository.findAll()).thenReturn(List.of(music));

        List<Music> result = service.getMusics();

        assertEquals(1, result.size());
        assertEquals("Song Test", result.get(0).getTitle());
    }

    @Test
    void test_getMusic_found() throws Exception {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(music));

        Music result = service.getMusic(1);

        assertEquals("Artist Test", result.getArtist());
    }

    @Test
    void test_getMusic_notFound() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.getMusic(1));
    }
}
