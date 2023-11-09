package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.CardDatabase;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AllCardViewController {

    @FXML
    private VBox allCardsVBox;

    @FXML
    private void initialize() {

    }

    @FXML
    private void switchToHomepage() throws IOException {
        MainApp.setRoot("mainHomepage");
    }


}
