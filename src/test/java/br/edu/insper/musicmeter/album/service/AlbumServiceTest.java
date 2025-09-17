package br.edu.insper.musicmeter.album.service;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.repository.AlbumRepository;
import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
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
class AlbumServiceTest {

    @InjectMocks
    private AlbumService service;

    @Mock
    private AlbumRepository repository;

    private Album album;

    @BeforeEach
    void setup() {
        album = new Album(1, "Test Album", "Test Artist", null, 5);
    }

    @Test
    void test_getAlbums() {
        Mockito.when(repository.findAll()).thenReturn(List.of(album));

        List<Album> result = service.getAlbums();

        assertEquals(1, result.size());
        assertEquals("Test Album", result.get(0).getTitle());
    }

    @Test
    void test_getAlbum_found() throws Exception {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(album));

        Album result = service.getAlbum(1);

        assertEquals("Test Artist", result.getArtist());
    }

    @Test
    void test_getAlbum_notFound() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.getAlbum(1));
    }
}
