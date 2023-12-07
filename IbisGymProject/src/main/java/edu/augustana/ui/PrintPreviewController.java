package edu.augustana.ui;


import edu.augustana.data.Card;

import edu.augustana.data.LessonPlan;
import edu.augustana.data.PrintManager;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrintPreviewController {


    @FXML
    private Button btnBack;
    @FXML
    private Group printGroup;
    @FXML
    private FlowPane fpEventLayout;
    @FXML
    private VBox vboxPrintLayout;
    private PrintManager printedVBox;


    private LessonPlan currentLessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

    @FXML
    private void initialize() {
        this.printedVBox = new PrintManager(vboxPrintLayout);
        for (Card card: currentLessonPlan.getOneEvent().getCards()) {

            ImageView cardImage = new ImageView(card.getImage());
            cardImage.setFitHeight(280);
            cardImage.setFitWidth(340);
            fpEventLayout.
            fpEventLayout.getChildren().add(cardImage);

        }
    }

    @FXML
    private void btnActionPrint() {
        printedVBox.printPage();
    }


    @FXML
    private void switchToNewLessonCreationPage(){
        MainApp.switchToNewLessonCreationPage();
    };

}
