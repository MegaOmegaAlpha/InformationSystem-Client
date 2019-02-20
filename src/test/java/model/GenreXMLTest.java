package model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class GenreXMLTest {

    @Test
    public void add() {
        GenreXML genreXML = new GenreXML(new File("target/addGenreTest"));
        for (int i = 1; i < 6; i++) {
            genreXML.add(new Genre("Genre" + i));
        }
    }

    @Test
    public void update() {
        GenreXML genreXML = new GenreXML(new File("target/updateGenreTest"));
        for (int i = 1; i < 6; i++) {
            genreXML.add(new Genre("Genre" + i));
        }
        genreXML.update(genreXML.getGenreIdByName("Genre2"), "Rock");
    }

    @Test
    public void delete() {
        GenreXML genreXML = new GenreXML(new File("target/deleteGenreTest"));
        for (int i = 1; i < 6; i++) {
            genreXML.add(new Genre("Genre" + i));
        }
        TrackXML trackXML = new TrackXML(new File("target/tracksTest"));
        genreXML.delete(genreXML.getGenreIdByName("Rock"), trackXML);
    }

    @Test
    public void getGenre() {
        GenreXML genreXML = new GenreXML(new File("target/getGenreTest"));
        for (int i = 1; i < 6; i++) {
            genreXML.add(new Genre("Genre" + i));
        }
        Assert.assertEquals("Genre2", genreXML.getGenre(genreXML.getGenreIdByName("Genre2")).getName());
    }

    @Test
    public void getAll() {
        GenreXML genreXML = new GenreXML(new File("target/getAllTest"));
        for (int i = 1; i < 6; i++) {
            genreXML.add(new Genre("Genre" + i));
        }
        List<Genre> genreList = genreXML.getAll();
        Assert.assertEquals(5, genreList.size());
    }
}