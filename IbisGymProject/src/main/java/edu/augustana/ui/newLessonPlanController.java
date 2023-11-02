package edu.augustana.ui;

import java.io.IOException;
import java.security.cert.PolicyNode;

import edu.augustana.data.Course;
import edu.augustana.data.LessonPlan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

public class newLessonPlanController {

    @FXML
    public Label lessonPlanName;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView source;
    @FXML
    private ImageView target;
    @FXML
    public VBox targetVBox;


    public newLessonPlanController() {

    }


    @FXML private Label lessonPlanName = new Label();


    @FXML private ImageView imageView;
    @FXML private ImageView source;
    @FXML private ImageView target;
    @FXML public VBox targetVBox;

    @FXML
    private void initialize(){
        this.lessonPlanName.setText(Course.currentLessonPlan.getLessonTitle());
    }

    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("mainHomepage");
    }


    @FXML
    void handleDragDetection(MouseEvent event) {
        sourceDetect();
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(source.getImage());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()) {
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

    @FXML
    void sourceDetect() {
        source.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                source = (ImageView) event.getSource();
            }
        });

    }
}









