package controller;

import model.GenreDAO;
import model.Track;
import model.TrackDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class UITrackListImpl implements UITrackList {

    private TrackDAO source;
    private GenreDAO genreFile;
    private List<UITrack> tracks;

    private List<UITrack> changedTracks;
    private List<UITrack> newTracks;
    private List<UITrack> deleteTracks;

    /**
     * Class constructor.
     * @param source source trackfile
     * @param genreFile source genrefile
     */
    public UITrackListImpl(TrackDAO source, GenreDAO genreFile) {
        this.source = source;
        this.genreFile = genreFile;
        changedTracks = new ArrayList<>();
        newTracks = new ArrayList<>();
        deleteTracks = new ArrayList<>();
        synchronize();
    }

    /**
     * Returns a new list of all tracks in a file.
     * This list may not match "tracks", only changes already synchronized
     * will be visible.
     * @return new list of tracks in a file
     */
    private List<UITrack> getAll(){
        List<UITrack> newList;
        List<Track> sourceList = source.getAll();

        newList = sourceList.stream().map(x -> new UITrackImpl(x, genreFile)).collect(Collectors.toList());

        return newList;
    }

    /**
     * Transfers any changes made to the tracklist to the trackfile itself.
     */
    @Override
    public void synchronize() {
        // Updating the trackfile
        for (UITrack item : changedTracks) {
            String album;
            if (item.getAlbum().contentEquals("no_album")) album = null;
            else album = item.getAlbum();

            List<UUID> genres;
            List<String> genreSource = item.getGenres();
            TODO:
            genres = genreSource.stream().map(x -> genreFile.getGenreIdByName(x)).collect(Collectors.toList());

            Track track = new Track(item.getName(), item.getArtists(), Optional.ofNullable(album), UITrackUtils.stringTimeToInt(item.getDuration()), genres);
            source.update(item.getId(), track);
        }
        for (UITrack item : deleteTracks) {
            source.delete(item.getId());
        }
        for (UITrack item : newTracks) {
            String album;
            if (item.getAlbum().contentEquals("no_album")) album = null;
            else album = item.getAlbum();

            List<UUID> genres;
            List<String> genreSource = item.getGenres();
            genres = genreSource.stream().map(x -> genreFile.getGenreIdByName(x)).collect(Collectors.toList());

            Track track = new Track(item.getName(), item.getArtists(), Optional.ofNullable(album), UITrackUtils.stringTimeToInt(item.getDuration()), genres);
            source.add(track);
        }
        changedTracks.clear();
        deleteTracks.clear();
        newTracks.clear();

        // Get new track list to make sure it matches the trackfile even if something went wrong
        tracks = getAll();

    }
    @Override
    public void markAsNew(UITrack track) {
        newTracks.add(track);
        tracks.add(track);
    }

    @Override
    public void setTracks(List<UITrack> tracks) {
        this.tracks = tracks;
    }

    /**
     * @return length of the tracklist
     */
    @Override
    public int size(){
        return tracks.size();
    }

    /**
     * Returns a list of tracks that match given parameters.
     * @param artists a string of artists
     * @param album a string of names
     * @param name a string with the track name
     * @param duration duration of track in string form
     * @param genre genres in string form
     * @return a new list of filtered tracks
     */
    @Override
    public List<UITrack> find(String artists, String album, String name, String duration, String genre) {
        return  tracks.stream().filter(x -> x.getArtists().contains(artists)).filter(x -> x.getAlbum().contains(album))
                .filter(x -> x.getName().contains(name)).filter(x -> x.getDuration().contentEquals(duration))
                .filter(x -> x.getGenres().contains(genre)).collect(Collectors.toList());
    }

    /**
     * Returns the list of tracks.
     * Never use this to make changes, they will not be saved.
     * @return reference to "tracks"
     */
    @Override
    public List<UITrack> getTracks() {
        return tracks;
    }

    /**
     * Marks a track for removal from trackfile and removes it from the tracklist.
     * Only saves changes upon calling the synchronize method.
     * @param track track that needs to be removed
     */
    @Override
    public void delete(UITrack track) {
        deleteTracks.add(track);
        tracks.remove(track);
    }

    /**
     * Marks the track as changed.
     * Only saves changes upon calling the synchronize method.
     * @param track track that was altered
     */
    @Override
    public void markAsChanged(UITrack track) {
        changedTracks.add(track);
    }

    /**
     * Adds a track to the tracklist and marks it as new.
     * Only saves changes upon calling the synchronize method.
     * @return new track
     */
    @Override
    public UITrack newTrack() {
        UITrackImpl track = new UITrackImpl(genreFile);
        newTracks.add(track);
        tracks.add(track);
        return track;
    }
}
