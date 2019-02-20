package controllerFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import controller.UITrackListFactory;

public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("/fxml/MainWindow.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("en")));
        Parent fxmlMain = fxmlLoader.load();
        MainWindow mainWindow = fxmlLoader.getController();
        mainWindow.setMainStage(primaryStage);
        primaryStage.setTitle(ResourceBundle.getBundle("bundles.Locale", new Locale("en")).getString("music_library"));
        primaryStage.setScene(new Scene(fxmlMain, 670, 450));
        primaryStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
