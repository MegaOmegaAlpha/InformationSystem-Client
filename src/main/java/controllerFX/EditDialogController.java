package controllerFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.UITrack;
import controller.UITrackList;
import controller.UITrackUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import utils.DialogManager;


public class EditDialogController implements Initializable{

    private UITrack track;
    ResourceBundle resourceBundle;

    @FXML
    private MenuButton btnMenu;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField textGenre;
    @FXML
    private TextField textDuration;
    @FXML
    private TextField textAlbum;
    @FXML
    private TextField textArtists;
    @FXML
    private TextField textName;
    @FXML
    private CheckMenuItem btnBlues;
    @FXML
    private CheckMenuItem btnCountry;
    @FXML
    private CheckMenuItem btnJazz;
    @FXML
    private CheckMenuItem btnRock;
    @FXML
    private CheckMenuItem btnRap;
    @FXML
    private CheckMenuItem btnClassic;

    private boolean add = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resourceBundle = resources;
    }

    public void btnCloseAction(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
    }

    public void closeAction(ActionEvent actionEvent) {
        track = null;
        Node source = (Node)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent){
        if (!checkValues()) { return; }
        List<String> genre = new ArrayList<String>();
        track.setName(textName.getText());
        track.setAlbum(textAlbum.getText());
        track.setArtists(UITrackUtils.stringToList(textArtists.getText(), ";"));
        track.setDuration(textDuration.getText());
        if (btnBlues.isSelected()){
            genre.add("Blues");
        }
        if (btnRock.isSelected()){
            genre.add("Rock");
        }
        if (btnRap.isSelected()){
            genre.add("Rap");
        }
        if (btnJazz.isSelected()){
            genre.add("Jazz");
        }
        if (btnClassic.isSelected()){
            genre.add("Classic");
        }
        if (btnCountry.isSelected()){
            genre.add("Country");
        }
        track.setGenre(genre);
        add = true;
        btnCloseAction(actionEvent);
    }

    public void setTrack(UITrack track, boolean add) {
        this.track = track;
        if (add) {
            return;
        }
        textName.setText(track.getName());
        textArtists.setText(UITrackUtils.listToString(track.getArtists(), ";"));
        textAlbum.setText(track.getAlbum());
        textDuration.setText(track.getDuration());
        btnBlues.setSelected(false);
        btnCountry.setSelected(false);
        btnClassic.setSelected(false);
        btnJazz.setSelected(false);
        btnRap.setSelected(false);
        btnRock.setSelected(false);
        //textGenre.setText(UITrackUtils.listToString(track.getGenres(), ";"));
    }

    public UITrack getTrack() {
        if(add) return track;
        else return null;
    }


    private boolean checkValues(){
        if (textName.getText().trim().length() == 0
                || textArtists.getText().trim().length() == 0
                || textDuration.getText().trim().length() == 0){
            DialogManager.showErrorDialog(resourceBundle.getString("error"), resourceBundle.getString("fill_field"));
            return false;
        }
        return true;
    }


}
