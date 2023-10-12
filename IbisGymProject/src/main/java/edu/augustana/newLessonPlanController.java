package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;

public class newLessonPlanController {

    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("primary");
    }
}