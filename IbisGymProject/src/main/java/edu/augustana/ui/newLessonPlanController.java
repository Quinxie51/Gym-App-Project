package edu.augustana.ui;
import java.io.IOException;
import java.security.cert.PolicyNode;
import edu.augustana.data.Card;
import edu.augustana.data.Course;
import edu.augustana.data.LessonPlan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static edu.augustana.data.CardDatabase.getAllCards;

public class newLessonPlanController {

    @FXML
    private ListView<Card> cardListView;
    @FXML
    public Label lessonPlanName;
    @FXML
    private ImageView imageView;
    @FXML
    public GridPane gridPane;

    @FXML
    private ImageView target;

    public newLessonPlanController() {

    }

    @FXML
    private void initialize() {
        this.lessonPlanName.setText(Course.currentLessonPlan.getLessonTitle());
        // Code: title [gender]
        // getReadableList ^^^
        cardListView.getItems().addAll(getAllCards());
        System.out.println(getAllCards());
        GridPane gridPane = new GridPane();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("mainHomepage");
    }


    @FXML
    void handleDragDetection(MouseEvent event) {
        Card selectedCard = cardListView.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            Dragboard db = cardListView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent cb = new ClipboardContent();
            cb.putString(selectedCard.getImagePath());
            db.setContent(cb);
            event.consume();
        }
    }


    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    void handleImageDropped(DragEvent event) {
        String imagePath = event.getDragboard().getString();
        Image image = new Image("file:CardPack/DEMO1Pack/" + imagePath);
        imageView.setImage(image);
    }
}

/*
    @FXML
    void handleImageDropped(DragEvent event) {
        boolean emptyCellFound = false;
        for (int row = 0; row < gridPane.getRowCount(); row++) {
            for (int col = 0; col < 3; col++) {
                int finalCol = col;
                int finalRow = row;
                if (gridPane.getChildren().stream().noneMatch(node -> GridPane.getRowIndex(node) == finalRow && GridPane.getColumnIndex(node) == finalCol)) {
                    emptyCellFound = true;
                    String imagePath = event.getDragboard().getString();
                    Image image = new Image("file:CardPack/DEMO1Pack/" + imagePath);
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    gridPane.add(imageView, finalCol, finalRow);
                    break;
                }
            }
        }
    }
}
*/

   /*         if (emptyCellFound) {
                break;
            }
        }
    }

            // If no empty cell was found, create a new row
            if (!emptyCellFound) {
                int newRow = gridPane.getRowCount();
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, newRow);
            }

            // Add the new ImageView to the GridPane
            gridPane.getChildren().add(imageView);

            event.setDropCompleted(true);
            event.consume();
        }
    }
*/


















