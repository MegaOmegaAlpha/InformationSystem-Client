package controller;

import model.Genre;
import model.GenreXML;
import model.TrackXML;

import java.io.File;

public class UITrackListFactory {
    /**
     * Returns a new instance of a tracklist.
     * Creates a new genrefile if it doesn't exist.
     * @param trackPath path to the track file
     * @param genrePath path to the genre file
     * @return new tracklist
     */
    public UITrackList getUITrackList(String trackPath, String genrePath) {
        if (trackPath == null || genrePath == null) {
            //return new UITrackListImpl(new TestTrackFile(), new TestGenreFile());
            return null;
        } else {
            GenreXML genreFile;
            File file = new File(genrePath);
            if(!file.exists()) {
                genreFile = new GenreXML(file);
                genreFile.add(new Genre("Blues"));
                genreFile.add(new Genre("Rock"));
                genreFile.add(new Genre("Rap"));
                genreFile.add(new Genre("Jazz"));
                genreFile.add(new Genre("Classic"));
                genreFile.add(new Genre("Country"));
            } else {
                genreFile = new GenreXML(file);
            }
            return new UITrackListImpl(new TrackXML(new File(trackPath)), genreFile);
        }
    }
}
