package edu.augustana.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

public class newLessonPlanController {

    @FXML public Label lessonPlanName;
    @FXML private ImageView imageView;
    @FXML private VBox targetVBox;
    @FXML private ImageView dragBoard;
    @FXML private ImageView source;
    @FXML private ImageView target;


    public newLessonPlanController(){

    }
    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    public void setLessonPlanName(String text) {
        lessonPlanName.setText(text);
    }

    @FXML
    void handleDragDetection(MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(source.getImage());
        db.setContent(cb);
        event.consume();
    }
    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    void handleImageDropped(DragEvent event) {
        Image image = event.getDragboard().getImage();
        target.setImage(image);

    }

}

