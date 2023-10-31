package edu.augustana.ui;

import java.io.IOException;
import java.security.cert.PolicyNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

public class newLessonPlanController {

    @FXML public Label lessonPlanName;
    @FXML private ImageView imageView;
    @FXML private ImageView source;
    @FXML private ImageView target;
    @FXML public VBox targetVBox;



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
        Image newImage = event.getDragboard().getImage();

        ObservableList<ImageView> imageViewList = FXCollections.observableArrayList();
        if (imageViewList.size() > 0) {
            Image lastImage = imageViewList.get(imageViewList.size() - 1).getImage();
            for (int i = imageViewList.size() - 1; i > 0; i--) {
                ImageView currentImageView = imageViewList.get(i - 1);
                ImageView nextImageView = imageViewList.get(i);
                currentImageView.setImage(nextImageView.getImage());
            }
            ImageView newImageView = new ImageView(lastImage);
            imageViewList.get(0).setImage(newImage);
            imageViewList.add(0, newImageView);
        } else {
            // Assuming target is the ImageView that you want to set the image to
            target.setImage(newImage);
        }
    }
}









