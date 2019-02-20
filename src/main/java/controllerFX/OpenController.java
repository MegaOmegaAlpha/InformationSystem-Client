package controllerFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;


public class OpenController {
    private static File file;
    String trackName;

    @FXML
    TextField txtField;


    public static File openAction(Stage mainStage){
        //TODO Create message ID_GET

        return file;
    }

    public void closeAction(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        trackName = txtField.getText();
        System.out.println(trackName);
        closeAction(actionEvent);
    }

}
