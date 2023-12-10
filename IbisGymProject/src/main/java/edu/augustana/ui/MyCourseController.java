package edu.augustana.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class MyCourseController {

    private HashMap<String, File> fileMap = new HashMap<>();;
    @FXML
    private ListView<String> lessonListView;

    @FXML
    private Button newLessonButton;

    @FXML
    private TextField searchBar;
    @FXML
    private Button importCourse;


    @FXML
    void handleImportCourse(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Your Course Folder");

        // Set initial directory (optional)
        // directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        Window mainWindow = lessonListView.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(mainWindow);
        if (selectedDirectory != null) {
            // Perform actions with the selected folder
            System.out.println("Selected folder: " + selectedDirectory.getAbsolutePath());

            File[] courseFiles = selectedDirectory.listFiles();
            if (courseFiles != null) {
                for (File file : courseFiles) {
                    if (file.isFile() && file.getName().endsWith(".gymPlan")) {
                        fileMap.put(file.getName(), file);
                        lessonListView.getItems().add(file.getName());
                    }
                }
            }
        } else {
            System.out.println("No folder selected.");
        }
    }


    @FXML
    private void handleOpenFromList() throws IOException {
        String selectedFileName = lessonListView.getSelectionModel().getSelectedItem();
        if (selectedFileName != null) {
            MainApp.openCurrentCourseFromFile(fileMap.get(selectedFileName));
            MainApp.getCurrentCourse().getOneLessonPlan().didOpenFromList();
            MainApp.switchToNewLessonCreationPage();
        }
    }

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
