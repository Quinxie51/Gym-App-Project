package edu.augustana.ui;
import java.io.IOException;
import java.util.*;

import edu.augustana.data.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static edu.augustana.data.CardDatabase.getAllCards;

public class newLessonPlanController {

    @FXML
    private ListView<Card> cardListView;

    @FXML
    private VBox eventFilterOptionsVBox;
    @FXML
    private VBox genderFilterOptionsVBox;

    @FXML
    private MenuItem printMenuItem;
    @FXML
    public Label lessonPlanName;
    @FXML
    private ImageView targetImageView;

    @FXML
    private FlowPane lessonFlowPane;

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

        for (String eventOption : CardDatabase.getEventSet()) {
            CheckBox cBox = new CheckBox(eventOption);
            cBox.setOnAction(e -> updateFilterResults());
            eventFilterOptionsVBox.getChildren().add(cBox);
        }
        System.out.println(getAllCards());
        GridPane gridPane = new GridPane();
    }

    private void updateFilterResults() {
        List<CardFilter> allFilters= new ArrayList<>();

        for (Node node:  genderFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                allFilters.add(new GenderFilter(cBox.getText()));
            }
        }

        List<String> selectedEvents = new ArrayList<>();
        for (Node node: eventFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedEvents.add(cBox.getText());
            }
        }
        allFilters.add(new EventFilter(selectedEvents));

        // make a new CombinedAndFilter, and apply it to all your cards to get the filtered set
        // update the UI to display only those cards that were filtered
    }

    @FXML
    private void switchToHomepage() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void handleEventFilter() {

        CardFilter event = new EventFilter("hi");
    }

    public static void createMaps() {
        Set<String> cardSet = new HashSet<>();
        for (Card card : getAllCards()) {
            cardSet.add(card.getEvent());
        }
        for (String card : cardSet) {

        }
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
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(80);
        lessonFlowPane.getChildren().add(imageView);

    }



   /*         if (emptyCellFound) {
                break;
            }
        }
    }

<<<<<<< HEAD
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


