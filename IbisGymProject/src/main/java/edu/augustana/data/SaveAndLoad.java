package edu.augustana.data;

import edu.augustana.ui.MainApp;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class SaveAndLoad {
     public void loadCourse(FlowPane flowPane) throws IOException {
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Open Course File");
         FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Course (*.gymCourse)", "*.gymCourse");
         fileChooser.getExtensionFilters().add(filter);
         Window mainWindow = flowPane.getScene().getWindow();
         File chosenFile = fileChooser.showOpenDialog(mainWindow);
         if (chosenFile != null) {
             MainApp.openCurrentCourseFromFile(chosenFile); //make a try catch
             //refreshLessonView();
         }
     }
}
