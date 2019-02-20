package controllerFX;

import controller.UITrack;
import controller.UITrackImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Track;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

public class TestData {
    /*
    public ObservableList<UITrack> getAllTest() throws IOException, ClassNotFoundException{
        ObservableList<UITrack> tracks = FXCollections.observableArrayList();
        tracks.add(new UITrackImpl(new Track("Pam", new LinkedList<String>(Arrays.asList(new String[] {"Dima", "PAvel"})), Optional.of("Sad"),
                32, null)));
        tracks.add(new UITrackImpl(new Track("Ram", new LinkedList<String>(Arrays.asList(new String[] {"Artem"})), Optional.of("wert"),
                56, null)));
        tracks.add(new UITrackImpl(new Track("Lal", new LinkedList<String>(Arrays.asList(new String[] {"Anton"})), Optional.of("adf"),
                12, null)));
        tracks.add(new UITrackImpl(new Track("Lya", new LinkedList<String>(Arrays.asList(new String[] {"Fox"})), Optional.of("etbc"),
                37, null)));
        return tracks;
    }
    */
}
