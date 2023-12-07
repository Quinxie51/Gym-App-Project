package edu.augustana.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class MyCourseController {

    @FXML
    private ListView<?> cardListView;

    @FXML
    private Button newLessonButton;

    @FXML
    private TextField searchBar;

    @FXML
    private void handleCreateNewLesson() throws IOException {
        //Stack overflow code
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Lesson");
        dialog.setHeaderText("New lesson name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String lessonTitle = result.get();
            MainApp.getCurrentCourse().getOneLessonPlan().setLessonTitle(lessonTitle);
            MainApp.switchToNewLessonCreationPage();
        }
    }


    @FXML
    void handleDragDetection(MouseEvent event) {

    }

    @FXML
    void handleSearch(KeyEvent event) {

    }

    @FXML
    void menuActionOpen(ActionEvent event) {

    }

    @FXML
    void menuActionSave(ActionEvent event) {

    }

    @FXML
    void menuActionSaveAs(ActionEvent event) {

    }

    @FXML
    void switchToHomepage(MouseEvent event) {

    }

    @FXML
    void switchToPrintPreview(ActionEvent event) {

    }

}
