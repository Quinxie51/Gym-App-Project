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
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static edu.augustana.data.CardDatabase.allCards;
import static edu.augustana.data.CardDatabase.getAllCards;

public class newLessonPlanController {

    @FXML
    private ListView<Card> cardListView;
    @FXML
    private MenuItem printMenuItem;
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
        // Code: title [gender]
        // getReadableList ^^^
        cardListView.getItems().addAll(getAllCards());
        System.out.println(getAllCards());
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
                    int finalRow = row;
                    int finalCol = col;
                    if (gridPane.getChildren().stream().noneMatch(node -> GridPane.getRowIndex(node) == finalRow && GridPane.getColumnIndex(node) == finalCol)) {
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

    }

    @FXML
    private void menuActionPrint() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newLessonCreationPage.fxml"));
            Node fxmlContent = loader.load();

            // Create a printing job
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null) {
                // Set the printer and page layout
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
                printerJob.getJobSettings().setPageLayout(pageLayout);

                // Fit the content to the page size
                Scene scene = MainApp.getScene();
                scene.getRoot().resize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
                scene.getWindow().setWidth(pageLayout.getPrintableWidth());
                scene.getWindow().setHeight(pageLayout.getPrintableHeight());

                boolean printed = printerJob.printPage(fxmlContent);
                if (printed) {
                    printerJob.endJob();
                }

                scene.getRoot().resize(scene.getWidth(), scene.getHeight());
                scene.getWindow().setWidth(scene.getWidth());
                scene.getWindow().setHeight(scene.getHeight());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}















