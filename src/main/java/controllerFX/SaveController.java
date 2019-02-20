package controllerFX;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveController {

    private static File file;
    public static File saveAs(Stage mainStage) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Files", "(*.*)");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showSaveDialog(mainStage);

        return file;
    }
}
