package edu.augustana.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class MainHomepageController {

    @FXML
    private void handleLibrary() throws IOException {
        MainApp.switchToLibrary();

    }
}
