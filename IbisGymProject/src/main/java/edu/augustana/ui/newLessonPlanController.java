package edu.augustana.ui;

import java.io.IOException;

import edu.augustana.ui.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DataFormat;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class newLessonPlanController {
    //datafields


    @FXML public Label lessonPlanName;
    @FXML private ImageView imageView;

    public newLessonPlanController(){

    }
    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    public void setLessonPlanName(String text) {
        lessonPlanName.setText(text);
    }

}

