package model;

import java.util.List;
import java.util.UUID;

public interface GenreDAO {

    void add(Genre newGenre);
    void update(UUID id, String newName);
    void delete(UUID id, TrackXML toDelete);
    Genre getGenre(UUID id);
    List<Genre> getAll();
    UUID getGenreIdByName(String name);
}
