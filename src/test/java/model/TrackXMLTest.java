package model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class TrackXMLTest {

    @Test
    public void add() {
        TrackXML trackXML = new TrackXML(new File("target/addTrackTest"));
        Random random = new Random();
        for (int i = 1; i < 51; i++) {
            trackXML.add(new Track("Best song" + i, new LinkedList<>(Arrays.asList(new String[] {"Andrey" + i})),
                    Optional.of("Album" + i), 60 + random.nextInt(1800 - 60 + 1),
                    new LinkedList<>(Arrays.asList(UUID.randomUUID()))));
        }
    }

    @Test
    public void update() {
        TrackXML trackXML = new TrackXML(new File("target/updateTrackTest"));
        Random random = new Random();
        for (int i = 1; i < 51; i++) {
            trackXML.add(new Track("Best song" + i, new LinkedList<>(Arrays.asList(new String[] {"Andrey" + i})),
                    Optional.of("Album" + i), 60 + random.nextInt(1800 - 60 + 1),
                    new LinkedList<>(Arrays.asList(UUID.randomUUID()))));
        }
        Track track = trackXML.getTrack(trackXML.getTrackKeyByName("Best song2"));
        track.setName("Best song ever");
        trackXML.update(track.getId(), track);
    }

    @Test
    public void delete() {
        TrackXML trackXML = new TrackXML(new File("target/deleteTrackTest"));
        Random random = new Random();
        for (int i = 1; i < 51; i++) {
            trackXML.add(new Track("Best song" + i, new LinkedList<>(Arrays.asList(new String[] {"Andrey" + i})),
                    Optional.of("Album" + i), 60 + random.nextInt(1800 - 60 + 1),
                    new LinkedList<>(Arrays.asList(UUID.randomUUID()))));
        }
        trackXML.delete(trackXML.getTrackKeyByName("Best song4"));
        Assert.assertEquals(49, trackXML.getAll().size());
    }

    @Test
    public void getTrack() {
        TrackXML trackXML = new TrackXML(new File("target/getTrackTest"));
        Random random = new Random();
        for (int i = 1; i < 51; i++) {
            trackXML.add(new Track("Best song" + i, new LinkedList<>(Arrays.asList(new String[] {"Andrey" + i})),
                    Optional.of("Album" + i), 60 + random.nextInt(1800 - 60 + 1),
                    new LinkedList<>(Arrays.asList(UUID.randomUUID()))));
        }
        Assert.assertEquals("Best song13", trackXML.getTrack(trackXML.getTrackKeyByName("Best song13")).getName());
    }

    @Test
    public void getAll() {
        TrackXML trackXML = new TrackXML(new File("target/getAllTrackTest"));
        Random random = new Random();
        for (int i = 1; i < 51; i++) {
            trackXML.add(new Track("Best song" + i, new LinkedList<>(Arrays.asList(new String[] {"Andrey" + i})),
                    Optional.of("Album" + i), 60 + random.nextInt(1800 - 60 + 1),
                    new LinkedList<>(Arrays.asList(UUID.randomUUID()))));
        }
        List<Track> trackList = trackXML.getAll();
        Assert.assertEquals(50, trackList.size());
    }
}