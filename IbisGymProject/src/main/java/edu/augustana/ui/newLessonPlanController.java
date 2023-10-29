package edu.augustana.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class newLessonPlanController {
    //datafields


    @FXML public Label lessonPlanName;
    @FXML private ImageView imageView;
    @FXML public TextField newLessonText;

    public newLessonPlanController(){

    }
    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    public void setLessonPlanName(String text) {
        lessonPlanName.setText(text);
    }

}

