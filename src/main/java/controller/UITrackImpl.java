package controller;

import model.GenreDAO;
import model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class UITrackImpl implements UITrack {

    private UUID id;
    private String name;
    private List<String> artists;
    private String album;
    private String duration;
    private List<String> genres;

    /**
     * Class constructor.
     * Initialises values of a new track with values from the source track.
     * @param source source track
     * @param genreFile source genrefile
     */
    public UITrackImpl(Track source, GenreDAO genreFile) {
        id = source.getId();
        name = source.getName();
        artists = source.getArtists();
        album = source.getAlbum().orElse("no_album");
        duration = UITrackUtils.intTimeToString(source.getDuration());

        List<String> newList;
        List<UUID> genreSource = source.getGenresId();
        newList = genreSource.stream().map(x -> genreFile.getGenre(x).getName()).collect(Collectors.toList());
        genres = newList;
    }

    /**
     * Class constructor.
     * Creates an empty track.
     * @param genreFile source genrefile
     */
    public UITrackImpl(GenreDAO genreFile){
        this(new Track("empty_track", new ArrayList<String>(), Optional.empty(), 0, new ArrayList<UUID>()), genreFile);
    }

    /**
     * Returns the id of the track.
     * @return id
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * Returns the name of the track.
     * @return track name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a list of artist names.
     * @return list of artists
     */
    @Override
    public List<String> getArtists() {
        return artists;
    }

    /**
     * Returns an album name.
     * @return album name
     */
    @Override
    public String getAlbum() {
        return album;
    }

    /**
     * Returns track duration in string form.
     * @return track duration
     */
    @Override
    public String getDuration() {
        return duration;
    }

    /**
     * Returns a list of genres of the track.
     * @return list of genres
     */
    @Override
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Sets the track name to a given value.
     * @param name new track name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the album name to a given value.
     * @param album new album name
     */
    @Override
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Sets the duration to a given value in string form.
     * @param duration new duration in string form
     */
    @Override
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Sets the duration to a given value in seconds.
     * @param duration new duration in seconds
     */
    @Override
    public void setDuration(int duration) {
        this.duration = UITrackUtils.intTimeToString(duration);
    }

    /**
     * Sets the list of artists to a given value.
     * @param artists new list of artists
     */
    @Override
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    /**
     * Sets the list of genres to a given value.
     * @param genres new list of genres
     */
    @Override
    public void setGenre(List<String> genres) {
        this.genres = genres;
    }
}
