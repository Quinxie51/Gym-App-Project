package edu.augustana.ui;

import javafx.fxml.FXML;

import java.io.IOException;

public class CreditsController {



    @FXML
    private void switchToHomepage() throws IOException {
        MainApp.setRoot("mainHomepage");
    }
}
