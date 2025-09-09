package br.edu.insper.musicmeter.music;

import br.edu.insper.musicmeter.album.Album;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Music
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String artist;
//    @ManyToOne
//    @JoinColumn(name = "music_album_id", referencedColumnName = "id")
    private Album album;
    private int rating;
}