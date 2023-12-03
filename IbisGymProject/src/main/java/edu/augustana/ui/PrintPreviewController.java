package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.LessonPlan;
import javafx.fxml.FXML;

public class PrintPreviewController {

    private LessonPlan currentLessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

    @FXML
    private void initialize() {

        for (Card card: currentLessonPlan.getCards()) {
            //thingholding .add(card)
        }

    }
}
