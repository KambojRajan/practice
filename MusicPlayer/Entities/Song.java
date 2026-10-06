package Entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(of = {"title", "artist"})
@Getter
@Setter
public class Song {
    private String title;
    private double duration;
    private double start;
    private Artist artist;
    private Album album;
}
