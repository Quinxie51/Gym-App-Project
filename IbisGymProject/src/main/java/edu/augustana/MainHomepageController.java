package edu.augustana;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

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
        MainApp.setRoot("folderPage");
    }

    @FXML
    private void backToHome() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void handleCreateNewLesson() throws IOException {

        // Load the FXML content for the popup
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newLessonPopup.fxml"));
        Parent popupContent;
        try {
            popupContent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Get the stage from any node in your scene
        Stage stage = (Stage) newLessonButton.getScene().getWindow();
        // Create a Popup instance and set its content
        Popup popup = new Popup();
        popup.getContent().add(popupContent);

        // Set the position of the popup relative to the stage
        popup.setX(stage.getX()+50); // Set your desired X position
        popup.setY(stage.getY()+50); // Set your desired Y position



        // Show the popup
        popup.show(stage);



        Button okayButton = (Button) popupContent.lookup("#okayDoneWithNewLessonPlanButton");
        okayButton.setOnAction(event -> {
            setNewLessonButton();
            popup.hide();

            // Add any additional logic you want to execute when the popup is closed.
        });

        // Close the popup when the "Done" button in the popup is clicked
        Button doneButton = (Button) popupContent.lookup("#quitPopUpButton");
        doneButton.setOnAction(event -> {
            popup.hide();

            // Add any additional logic you want to execute when the popup is closed.
        });
    }


    @FXML
    private void setNewLessonButton() {
        MainApp.switchToNewLessonCreationPage();
    }

    @FXML
    private void openAllCardsView() throws IOException {
        MainApp.setRoot("allCardsView");
    }
}
