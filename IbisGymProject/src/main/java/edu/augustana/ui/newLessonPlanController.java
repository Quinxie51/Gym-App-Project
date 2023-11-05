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
import javafx.scene.layout.GridPane;
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
    @FXML
    public GridPane gridPane = new GridPane();

    public newLessonPlanController() {

    }

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

        Image image = new Image("file:Image/" + source.getImage());
        ImageView imageView = new ImageView(image);

            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                int numRows = gridPane.getRowCount();
                int numCols = gridPane.getColumnCount();
                boolean emptyCellFound = false;

                for (int row = 0; row < numRows; row++) {
                    for (int col = 0; col < numCols; col++) {
                        if (gridPane.getChildren().stream().noneMatch(node -> GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)) {
                            ImageView newImageView = new ImageView(db.getImage());
                            gridPane.add(newImageView, col, row);
                            emptyCellFound = true;
                            break;
                        }
                    }
                    if (emptyCellFound) {
                        break;
                    }
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }


}















