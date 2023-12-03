package edu.augustana.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class MainHomepageController {

    // for mainHomepage.fxml
    @FXML
    private Button allCardsButton;
    @FXML
    private Button deletedButton;
    @FXML
    private Button favoritesButton;
    @FXML
    private Button folderButton;
    @FXML
    private Button newLessonButton;
    @FXML
    private Button qaButton;
    @FXML
    private Button printButton;
    @FXML
    private Button settingsButton;

    @FXML
    private HBox recentViewBox;

    @FXML
    Label newLessonPlanName = new Label();

    //for newLessonPopup.fxml
    @FXML
    private Button doneMakingLesson;
    @FXML
    private CheckBox folderCheckbox;
    @FXML
    private TextField folderText;
    @FXML
    private TextField newLessonText;

    @FXML
    private NewLessonPlanController newLessonPlanController = new NewLessonPlanController();

    @FXML
    private void initialize() {

    }


    @FXML
    private void openFolderView() throws IOException {
        MainApp.setRoot("folderPage");
    }

    @FXML
    private void backToHome() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void handleCreateNewLesson() throws IOException {
        //Stack overflow code
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Lesson");
        dialog.setHeaderText("New lesson name:");
        // Handle the result
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String lessonTitle = result.get();
            MainApp.getCurrentCourse().getOneLessonPlan().setLessonTitle(lessonTitle);
            MainApp.switchToNewLessonCreationPage();
        }
    }

    @FXML
    private void openAllCardsView() throws IOException {
        MainApp.setRoot("allCardsView");
    }

}
