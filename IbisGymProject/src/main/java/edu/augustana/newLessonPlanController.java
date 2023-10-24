package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class newLessonPlanController {
    //datafields


     @FXML public Label lessonPlanName;

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