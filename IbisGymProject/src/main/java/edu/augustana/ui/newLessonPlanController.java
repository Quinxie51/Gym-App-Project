package edu.augustana.ui;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


import edu.augustana.data.*;
import edu.augustana.data.Card;
import edu.augustana.data.CardFilter;
import edu.augustana.data.Course;
import edu.augustana.data.EventFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static edu.augustana.data.CardDatabase.allCards;
import static edu.augustana.data.CardDatabase.getAllCards;

public class newLessonPlanController {

    @FXML
    private ListView<Card> cardListView;

    @FXML
    private VBox eventFilterOptionsVBox;
    @FXML
    private VBox genderFilterOptionsVBox;

    @FXML
    private ObservableList<Card> observableCards = FXCollections.observableArrayList(allCards);

    //Events
    @FXML private CheckBox floor;
    @FXML private  CheckBox unevenBars;
    @FXML private CheckBox beam;



    @FXML
    private CheckBox beamEventCheck;

    private MenuItem printMenuItem;
    @FXML
    public Label lessonPlanName;
    @FXML
    private ImageView targetImageView;

    @FXML
    private FlowPane lessonFlowPane;

    @FXML
    private ImageView target;
    @FXML
    private TextField searchBar;


    public newLessonPlanController() {

    }

    @FXML
    private void initialize() {
        this.lessonPlanName.setText(Course.currentCourse.getOneLessonPlan().getLessonTitle());
        // Code: title [gender]
        // getReadableList ^^^
        cardListView.getItems().addAll(getAllCards());
        cardListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        for (String eventOption : CardDatabase.getEventSet()) {
            CheckBox cBox = new CheckBox(eventOption);
            cBox.setOnAction(e -> updateFilterResults());
            eventFilterOptionsVBox.getChildren().add(cBox);
        }
        System.out.println(getAllCards());
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
        if(!selectedEvents.isEmpty()){
            allFilters.add(new EventFilter(selectedEvents));
        }

        List<Card> filteredCards = CardDatabase.getAllCards();


        for (CardFilter filter: allFilters){
            filteredCards = filteredCards.stream().filter(filter::matches).collect(Collectors.toList());
        }
        cardListView.setItems(FXCollections.observableArrayList(filteredCards));

        // make a new CombinedAndFilter, and apply it to all your cards to get the filtered set
        // update the UI to display only those cards that were filtered
    }


    @FXML
    private void switchToHomepage() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void handleSearch() {
        String searchText = searchBar.getText().toLowerCase().trim(); // Get the text from the search bar

        if (searchText.isEmpty()) {
            // If the search bar is empty, display all cards
            cardListView.setItems((observableCards));
        } else {
            // Search and display matching cards for entire word
            List<Card> matchingCards = new ArrayList<>();
            for (Card card : allCards) {
                // Split the card title into words and check for an exact match
                String[] words = card.getTitle().toLowerCase().split("\\s+"); // Split title into words
                for (String word : words) {
                    if (word.equals(searchText)) { // Check if any word matches the search text
                        matchingCards.add(card);
                        break; // Add the card and move to the next card
                    }
                }
            }
            cardListView.setItems(FXCollections.observableArrayList(matchingCards));
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
        imageView.setFitWidth(180);
        imageView.setFitHeight(120);
        lessonFlowPane.setHgap(10);
        lessonFlowPane.setVgap(10);
        lessonFlowPane.getChildren().add(imageView);


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


