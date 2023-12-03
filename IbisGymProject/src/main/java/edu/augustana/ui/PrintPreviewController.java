package edu.augustana.ui;


import edu.augustana.data.Card;

import edu.augustana.data.LessonPlan;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class PrintPreviewController {



    @FXML
    private HBox hboxEventLayoutTop;
    @FXML
    private HBox hboxEventLayoutBottom;



    @FXML
    private void btnActionPrint() {
        //  vboxPage.printPage();
    }
    private LessonPlan currentLessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

    @FXML
    private void initialize() {

        for (Card card: currentLessonPlan.getCards()) {
            Image image = card.getImage();
            CardImageView cardImageView = new CardImageView(image,card);
            hboxEventLayoutTop.getChildren().add(cardImageView);


            //thingholding .add(card)
        }




    }
}
