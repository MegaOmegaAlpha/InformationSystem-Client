package model;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Track  implements Serializable {

    private static final long SerialVersionUID = -4862926644813433707L;

    private UUID id;

    private String name;
    private List<String> artists;
    private String album;
    private int duration;

    private List<UUID> genresId;

    public Track(String name, List<String> artists, Optional<String> album, int duration, List<UUID> genresId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.artists = artists;
        this.album = album.orElse(null);
        this.duration = duration;
        this.genresId = genresId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getArtists() {
        return artists;
    }

    public Optional<String> getAlbum() {
        return Optional.ofNullable(album);
    }

    public int getDuration() {
        return duration;
    }

    public List<UUID> getGenresId() {
        return genresId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public void setAlbum(Optional<String> album) {
        this.album = album.orElse(null);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenresId(List<UUID> genresId) {
        this.genresId = genresId;
    }
}
