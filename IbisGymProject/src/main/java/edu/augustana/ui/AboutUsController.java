package edu.augustana.ui;

import javafx.fxml.FXML;

import java.io.IOException;

public class AboutUsController {


    @FXML
    private void switchToHomepage() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void switchToCredits() throws IOException {
        MainApp.setRoot("Credits");
    }

}
