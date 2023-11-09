package edu.augustana.ui;

import java.io.File;
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
import javafx.event.ActionEvent;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import static edu.augustana.data.CardDatabase.*;

public class newLessonPlanController {

    @FXML
    private ListView<Card> cardListView;
    @FXML private Button deleteCard;

    @FXML
    private ObservableList<Card> observableCards = FXCollections.observableArrayList(allCards);
    private LessonPlan lessonPlan;
    @FXML private VBox eventFilterOptionsVBox;
    @FXML private VBox genderFilterOptionsVBox;
    @FXML private VBox categoryFilterOptionsVBox;
    @FXML private VBox levelFilterOptionsVBox;
    @FXML private VBox equipmentFilterOptionsVBox;
    @FXML private VBox modelSexFilterOptionsVBox;



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
    @FXML
    private VBox printedVbox;


    public newLessonPlanController() {

    }

    @FXML
    private void initialize() {
        this.lessonPlanName.setText(MainApp.getCurrentCourse().getOneLessonPlan().getLessonTitle());
        //lessonPlan.setLessonTitle(Course.currentCourse.getOneLessonPlan().getLessonTitle());
        // Code: title [gender]
        // getReadableList ^^^
        cardListView.getItems().addAll(getAllCards());
        cardListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (String genderOption : CardDatabase.getGenderSet()) {
            CheckBox cBox = new CheckBox(genderOption);
            cBox.setOnAction(e -> updateFilterResults());
            genderFilterOptionsVBox.getChildren().add(cBox);
        }


        for (String eventOption : CardDatabase.getEventSet()) {
            CheckBox cBox = new CheckBox(eventOption);
            cBox.setOnAction(e -> updateFilterResults());
            eventFilterOptionsVBox.getChildren().add(cBox);
        }


        for (String categoryOption : CardDatabase.getCategorySet()) {
            CheckBox cBox = new CheckBox(categoryOption);
            cBox.setOnAction(e -> updateFilterResults());
            categoryFilterOptionsVBox.getChildren().add(cBox);
        }

        for (String levelOption : CardDatabase.getLevelSet()) {
            CheckBox cBox = new CheckBox(levelOption);
            cBox.setOnAction(e -> updateFilterResults());
            levelFilterOptionsVBox.getChildren().add(cBox);
        }


        for (String equipmentOption : CardDatabase.getEquipmentSet()) {
            CheckBox cBox = new CheckBox(equipmentOption);
            cBox.setOnAction(e -> updateFilterResults());
            equipmentFilterOptionsVBox.getChildren().add(cBox);
        }



        for (String modelSexOption : CardDatabase.getModelSexSet()) {
            CheckBox cBox = new CheckBox(modelSexOption);
            cBox.setOnAction(e -> updateFilterResults());
            modelSexFilterOptionsVBox.getChildren().add(cBox);
        }

        System.out.println(getAllCards());
    }


    private void updateFilterResults() {
        List<CardFilter> allFilters = new ArrayList<>();


        //Gender start
        List<String> selectedGenders = new ArrayList<>();
        for (Node node : genderFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedGenders.add(cBox.getText());
            }
        }
        if (!selectedGenders.isEmpty()) {
            allFilters.add(new GenderFilter(selectedGenders));
        }
        //Gender end




        //Events start
        List<String> selectedEvents = new ArrayList<>();
        for (Node node : eventFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedEvents.add(cBox.getText());
            }
        }
        if (!selectedEvents.isEmpty()) {
            allFilters.add(new EventFilter(selectedEvents));
        }
        //Events end



        //Category start
        List<String> selectedCategory = new ArrayList<>();
        for (Node node : categoryFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedCategory.add(cBox.getText());
            }
        }
        if (!selectedCategory.isEmpty()) {
            allFilters.add(new CategoryFilter(selectedCategory));
        }
        //Category end

        //Level start
        List<String> selectedLevel = new ArrayList<>();
        for (Node node : levelFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedLevel.add(cBox.getText());
            }
        }
        if (!selectedLevel.isEmpty()) {
            allFilters.add(new LevelFilter(selectedLevel));
        }
        //Level end



        //Equipment start
        List<String> selectedEquipments = new ArrayList<>();
        for (Node node : equipmentFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedEquipments.add(cBox.getText());
            }
        }
        if (!selectedEquipments.isEmpty()) {
            allFilters.add(new EquipmentFilter(selectedEquipments));
        }
        //Equipment end


        //ModelSex start
        List<String> selectedModelSexes = new ArrayList<>();
        for (Node node : modelSexFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedModelSexes.add(cBox.getText());
            }
        }
        if (!selectedModelSexes.isEmpty()) {
            allFilters.add(new ModelSexFilter(selectedModelSexes));
        }
        //ModelSex end


        List<Card> filteredCards = CardDatabase.getAllCards();


        for (CardFilter filter : allFilters) {
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
        List<Card> selectedCards = cardListView.getSelectionModel().getSelectedItems();
        List<String> allIDs = new ArrayList<>();
        for (Card card : selectedCards) {
            allIDs.add(card.getUniqueID());
        }
        Dragboard db = cardListView.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(String.join("*", allIDs));
        db.setContent(cb);
        event.consume();
    }


    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }


    @FXML
    void handleImageDropped(DragEvent event) {
        String[] uniqueIDs = event.getDragboard().getString().split("\\*");
        for (String uniqueID : uniqueIDs) {
            Card card = CardDatabase.getCardFromUniqueID(uniqueID);
            MainApp.getCurrentCourse().getOneLessonPlan().addCard(card);
            // instead of adding each view at a time, we could
            // after the loop, clear everything from the lesson plan view
            // and recreate it in the right order, grouped by the event
            // of each card
        }
        refreshLessonView();
    }

    private void refreshLessonView() {
        Node firstThing = lessonFlowPane.getChildren().get(0);
        lessonFlowPane.getChildren().clear();
        lessonFlowPane.getChildren().add(firstThing);
        for (Card card : MainApp.getCurrentCourse().getOneLessonPlan().getCards()) {
            Image image = card.getImage();
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(180);
            imageView.setFitHeight(120);
            lessonFlowPane.setHgap(10); // should be set in scenebuilder
            lessonFlowPane.setVgap(10);
            lessonFlowPane.getChildren().add(imageView);

        }
    }

/*    @FXML
 *//*   private void actionDeleteCard() {
        LessonPlan cardToDelete = lessonFlowPane.getSelectionModel().getSelectedItem();
        if (cardToDelete!= null) {
            LessonPlan.getCurrentMovieLog().removeMovieWatchRecord(movieWatchToDelete);
        } else {
            new Alert(Alert.AlertType.WARNING, "Select a movie to delete first!").show();
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
                //scene.getRoot().resize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
                //scene.getWindow().setWidth(pageLayout.getPrintableWidth());
                //scene.getWindow().setHeight(pageLayout.getPrintableHeight());

                // Create a separate container for the content to print
                Pane printContainer = new Pane();
                printContainer.getChildren().add(fxmlContent);

                double x = 500; // X coordinate of the top-left corner of the area to print
                double y = -100; // Y coordinate of the top-left corner of the area to print
                double width = 900; // Width of the area to print
                double height = 750; // Height of the area to print

                // Adjust the layout of the printContainer
                printContainer.setLayoutX(-x);
                printContainer.setLayoutY(-y);
                printContainer.setPrefWidth(width);
                printContainer.setPrefHeight(height);

                boolean printed = printerJob.printPage(printContainer);
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

    @FXML
    private void menuActionOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Course (*.gymCourse)", "*.gymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonFlowPane.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        if (chosenFile != null) {
            try {
                MainApp.openCurrentCourseFromFile(chosenFile);
                refreshLessonView();
            } catch (IOException e) {
                //TODO: show error alert to user for failing to load file
            }
        }
    }


    @FXML
    private void menuActionSave(ActionEvent event) {

    }
    @FXML
    private void menuActionSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Course (*.gymCourse)", "*.gymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonFlowPane.getScene().getWindow();
        File chosenFile = fileChooser.showSaveDialog(mainWindow);
        saveCurrentCourseToFile(chosenFile);
    }
    private void saveCurrentCourseToFile(File chosenFile) {
        if (chosenFile != null) {
            try {
                MainApp.saveCurrentCourseToFile(chosenFile);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving movie log file: " + chosenFile).show();
            }
        }
    }

}


