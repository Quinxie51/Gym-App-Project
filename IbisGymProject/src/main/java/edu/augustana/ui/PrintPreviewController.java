package edu.augustana.ui;


import edu.augustana.data.Card;

import edu.augustana.data.LessonPlan;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PrintPreviewController {



    @FXML
    private HBox hboxEventLayout;
;



    @FXML
    private void btnActionPrint() {
        //  vboxPage.printPage();
    }
    private LessonPlan currentLessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

    @FXML
    private void initialize() {
        for (Card card: currentLessonPlan.getCards()) {

            ImageView cardImage = new ImageView(card.getImage());
            cardImage.setFitHeight(100);
            cardImage.setFitWidth(100);
            hboxEventLayout.getChildren().add(cardImage);


            //thingholding .add(card)
        }




    }
}
