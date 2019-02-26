package controllerFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable{

    boolean flag = false;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblText;
    @FXML
    private ImageView img;

    public void actionOk(ActionEvent actionEvent) {
        flag = true;
        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblText.setText("Are you sure you want to delete the selected track?");
        img.setImage(new Image("/fxml/warning.png"));
    }

}
