package edu.augustana;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class MainHomepageController {

    // for mainHomepage.fxml
    @FXML private Button allCardsButton;
    @FXML private Button deletedButton;
    @FXML private Button favoritesButton;
    @FXML private Button folderButton;
    @FXML private Button newLessonButton;
    @FXML private Button qaButton;
    @FXML private Button settingsButton;
    @FXML private TextField searchBar;

    //for newLessonPopup.fxml
    @FXML private Button doneMakingLesson;
    @FXML private CheckBox folderCheckbox;
    @FXML private TextField folderText;
    @FXML private TextField newLessonText;

    @FXML
    private void initialize() {
        //newLessonText.setOnKeyTyped(e -> handleQuestTextChange(e));
    }

    @FXML
    private void openFolderView() throws IOException {
        MainApp.setRoot("secondary");
    }

    @FXML
    private void backToHome() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void handleCreateNewLesson() throws IOException {
        MainApp.setRoot("newLessonPopup");
    }

    @FXML
    private void openAllCardsView() throws IOException {
        MainApp.setRoot("allCardsView");
    }
}
