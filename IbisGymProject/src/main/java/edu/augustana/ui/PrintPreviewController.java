package edu.augustana.ui;


import edu.augustana.data.Card;

import edu.augustana.data.LessonPlan;
import edu.augustana.data.PrintManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PrintPreviewController {



    @FXML
    private HBox hboxEventLayout;
    private PrintManager printedHBox;


    private LessonPlan currentLessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

    @FXML
    private void initialize() {
        this.printedHBox = new PrintManager(hboxEventLayout);
        for (Card card: currentLessonPlan.getCards()) {

            ImageView cardImage = new ImageView(card.getImage());
            cardImage.setFitHeight(100);
            cardImage.setFitWidth(100);
            hboxEventLayout.getChildren().add(cardImage);

        }
    }

    @FXML
    private void btnActionPrint() {
        printedHBox.printPage();
    }
}
