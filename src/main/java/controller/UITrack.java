package controller;

import model.*;

import java.util.List;
import java.util.UUID;


public interface UITrack {

    UUID getId();

    String getName();

    List<String> getArtists();

    String getAlbum();

    String getDuration();

    List<String> getGenres();

    void setName(String name);

    void setAlbum(String album);

    void setDuration(int duration);

    void setDuration(String duration);

    void setArtists(List<String> artists);

    void setGenre(List<String> genres);
}
