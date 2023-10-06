package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML private Button deletedButton;

    @FXML private Button favoritesButton;

    @FXML private Button folderButton;

    @FXML private Button newLessonButton;

    @FXML private Button primaryButton;

    @FXML private Button qaButton;

    @FXML private Button settingsButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
