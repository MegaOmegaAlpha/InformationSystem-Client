package controllerFX;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;

import java.util.Locale;
import java.util.ResourceBundle;

import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DialogManager;

import java.io.IOException;

public class MainWindow implements Initializable{

    private UITrackList trackList; //= new UITrackListFactory().getUITrackList("trackfile", "genrefile");

    private Stage mainStage;
    private ResourceBundle resourceBundle;

    @FXML
    private Label labelCount;
    @FXML
    private TableColumn<UITrack, String> clmnGenre;
    @FXML
    private TableColumn<UITrack, String> clmnDuration;
    @FXML
    private TableColumn<UITrack, String> clmnAlbum;
    @FXML
    private TableColumn<UITrack, String> clmnArtist;
    @FXML
    private TableColumn<UITrack, String> clmnName;
    @FXML
    private TableView tableSongsLibrary;



    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private Parent openDialog;
    private FXMLLoader openFxmlLoader = new FXMLLoader();
    private OpenController openController;
    private Stage openStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        Message message = new Message();
        message.actionID = Message.ActionID.ID_INIT;
        message.trackfile = "trackfile";
        message.genrefile = "genrefile";
        try {
            Socket socket = new Socket("localhost", 0066);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
            trackList = new UITrackListFactory().getUITrackList(message.trackfile, message.genrefile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        updateCountLabel();
//        initListeners();

        clmnDuration.setCellValueFactory(new PropertyValueFactory<UITrack, String>("duration"));
        clmnAlbum.setCellValueFactory(new PropertyValueFactory<UITrack, String>("album"));
        clmnName.setCellValueFactory(new PropertyValueFactory<UITrack, String>("name"));
        clmnArtist.setCellValueFactory(new PropertyValueFactory<UITrack, String>("artists"));
        clmnGenre.setCellValueFactory(new PropertyValueFactory<UITrack, String>("genres"));
//        tableSongsLibrary.setItems(FXCollections.observableArrayList(trackList.getTracks()));

        try {
            openFxmlLoader.setLocation(getClass().getResource("/fxml/OpenDialog.fxml"));
            openDialog = openFxmlLoader.load();
            openController = openFxmlLoader.getController();
            fxmlLoader.setLocation(getClass().getResource("/fxml/showDialog.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("en")));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateCountLabel(){
        labelCount.setText(resourceBundle.getString("number_of records") + ": " + trackList.size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if(!(source instanceof Button) && source instanceof MenuItem) {
            MenuItem clickedMenuItem = (MenuItem)source;
            File file;
            switch (clickedMenuItem.getId()){
                case "btnSave":
                    // TODO: move this to a seperate button
                    trackList.synchronize();
                    break;
                case "btnOpen":
                    if (openStage == null){
                        openStage = new Stage();
                        openStage.setTitle("Open");
                        openStage.setMinWidth(285);
                        openStage.setMinHeight(150);
                        openStage.setScene(new Scene(openDialog));
                    }
                    openStage.showAndWait();
                    file = OpenController.openAction(mainStage);
                    if (file != null){
                        trackList = new UITrackListFactory().getUITrackList(file.getAbsolutePath(), "genrefile");
                        mainStage.setTitle("Music library (" + file.getName() + ")");
                        tableSongsLibrary.setItems(FXCollections.observableArrayList(trackList.getTracks()));
                        updateCountLabel();
                    }
                    break;
            }
            return;
        }
        Button clickedButton = (Button)source;
        UITrack selectedTrack = (UITrack)tableSongsLibrary.getSelectionModel().getSelectedItem();
        String title = "";
        switch (clickedButton.getId()){
            case "btnAdd":
                title = resourceBundle.getString("add");
                editDialogController.setTrack(trackList.newTrack(), true);
                showDialog(title);

                tableSongsLibrary.setItems(FXCollections.observableArrayList(trackList.getTracks()));
                updateCountLabel();
                break;
            case "btnEdit":
                if (!trackIsSelected(selectedTrack)) {
                    return;
                }
                title = resourceBundle.getString("edit");
                editDialogController.setTrack(selectedTrack, false);
                showDialog(title);
                trackList.markAsChanged(selectedTrack);
                tableSongsLibrary.refresh();
                break;
            case "btnSelectDel":
                if (!trackIsSelected(selectedTrack)) {
                    return;
                }
                title = resourceBundle.getString("delete");
                trackList.delete(selectedTrack);
                tableSongsLibrary.setItems(FXCollections.observableArrayList(trackList.getTracks()));
                updateCountLabel();
                break;
        }
    }



    private void initListeners(){
        FXCollections.observableArrayList(trackList.getTracks()).addListener(new ListChangeListener<UITrack>() {
            @Override
            public void onChanged(Change<? extends UITrack> c) {
                updateCountLabel();
            }
        });
        tableSongsLibrary.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    editDialogController.setTrack((UITrack)tableSongsLibrary.getSelectionModel().getSelectedItem(), false);
                    showDialog(resourceBundle.getString("edit_record"));
                }
            }
        });
    }

    private void showDialog(String title) {
        if (editDialogStage == null){
            editDialogStage = new Stage();
            editDialogStage.setTitle(title);
            editDialogStage.setMinHeight(320);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();
        tableSongsLibrary.refresh();

    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
        mainStage.setTitle("Music library (trackfile)");
    }


    private boolean trackIsSelected(UITrack selectedTrack){
        if (selectedTrack == null){
            DialogManager.showErrorDialog(resourceBundle.getString("error"), resourceBundle.getString("select_track"));
            return false;
        }
        return true;
    }


}
