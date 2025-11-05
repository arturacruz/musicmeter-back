package br.edu.insper.musicmeter.album.service;

import br.edu.insper.musicmeter.album.Album;
import br.edu.insper.musicmeter.album.dto.AlbumDTO;
import br.edu.insper.musicmeter.album.repository.AlbumRepository;
import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Album saveAlbum(AlbumDTO album) {
        return albumRepository.save(album.to());
    }

    public Album updateAlbum(int id, AlbumDTO album) throws ObjectNotFoundException {
        Album model = getAlbum(id);
        Album updated = album.to();
        updated.setId(model.getId());
        saveAlbum(AlbumDTO.from(updated));
        return updated;
    }

    public Album deleteAlbum(int id) throws ObjectNotFoundException {
        Album album = getAlbum(id);
        albumRepository.deleteById(id);
        return album;
    }

    public Album getAlbum(Integer id) throws ObjectNotFoundException {
        Optional<Album> album = albumRepository.findById(id);
        if(album.isEmpty()) {
            throw new ObjectNotFoundException("");
        }
        return album.get();
    }
}
