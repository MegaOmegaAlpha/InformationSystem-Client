package model;

import java.util.List;
import java.util.UUID;

public interface TrackDAO {

    void add(Track newTrack);
    void update(UUID id, Track newTrack);
    void delete(UUID id);
    Track getTrack(UUID id);
    List<Track> getAll();
    UUID getTrackIdByName(String name);
}
