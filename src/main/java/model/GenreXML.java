package model;

import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GenreXML implements GenreDAO {

    private File file;
    private XStream xStream;
    private static final Logger logger = Logger.getLogger(GenreXML.class);

    public GenreXML(File file) {
        this.file = file;
        xStream = new XStream();
        xStream.alias("Genres", List.class);
        if (!file.exists()) {
            List<Genre> list = new ArrayList<>();
            try {
                xStream.toXML(list, new FileWriter(file));
            } catch (IOException e) {
                logger.error("IOException in constructor");
            }
        }
    }

    @Override
    public void add(Genre newGenre) {
        List<Genre> genres = getAll();
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            genres.add(newGenre);
            xStream.toXML(genres, printWriter);
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException in 'add'");
        } catch (IOException e) {
            logger.error("IOException in 'add'");
        }
    }

    @Override
    public void update(UUID id, String newName) {
        List<Genre> genres = getAll();
        for (Genre genre : genres) {
            if (genre.getId().equals(id)) {
                genre.setName(newName);
            }
        }
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            xStream.toXML(genres, printWriter);
        } catch (IOException e) {
            logger.error("IOException in 'update'");
        }
    }

    @Override
    public void delete(UUID id, TrackXML trackXML) {
        List<Genre> genres = getAll();
        for (Genre genre : genres) {
            if (genre.getId().equals(id)) {
                genres.remove(genre);
                break;
            }
        }
        try {
            xStream.toXML(genres, new FileWriter(file));
            List<Track> tracks = trackXML.getAll();
            for (Track track : tracks) {
                List<UUID> uuidList = track.getGenresId();
                for (UUID genreID  : uuidList) {
                    if (genreID.equals(id)) {
                        uuidList.remove(genreID);
                        break;
                    }
                }
            }
            xStream.toXML(tracks, new FileWriter(trackXML.getFile()));
        } catch (IOException e) {
            logger.error("IOException in 'delete'");
        }
    }

    @Override
    public Genre getGenre(UUID id) {
        List<Genre> genres = getAll();
        for (Genre genre : genres) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }
        return null;
    }

    public UUID getGenreIdByName(String name) {
        List<Genre> genreList = getAll();
        for (Genre genre : genreList) {
            if (genre.getName().equals(name)) {
                return genre.getId();
            }
        }
        return null;
    }

    @Override
    public List<Genre> getAll() {
        try {
            return (List<Genre>) xStream.fromXML(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException in 'getAll'");
        }
        return null;
    }
}
