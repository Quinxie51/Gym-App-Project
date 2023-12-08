package edu.augustana.ui;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import edu.augustana.data.*;
import edu.augustana.data.Card;
import edu.augustana.data.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import static edu.augustana.data.CardDatabase.*;

public class NewLessonPlanController {

    @FXML
    private ObservableList<Card> observableCards = FXCollections.observableArrayList(allCards);
    @FXML
    private VBox eventFilterOptionsVBox;
    @FXML
    private VBox genderFilterOptionsVBox;
    @FXML
    private VBox categoryFilterOptionsVBox;
    @FXML
    private VBox levelFilterOptionsVBox;
    @FXML
    private VBox equipmentFilterOptionsVBox;
    @FXML
    private VBox modelSexFilterOptionsVBox;

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;
    @FXML
    private VBox lessonVbox;

    @FXML
    private CheckBox beamEventCheck;

    private MenuItem printMenuItem;
    @FXML
    public Label lessonPlanName;
    @FXML
    private ImageView targetImageView;

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<Card> cardListView;
    @FXML
    private Button deleteCard;
    @FXML
    private Button addEvent;
    @FXML
    private LessonPlan lessonPlan;

    private edu.augustana.ui.CardUndoRedoHandler undoRedoHandler;


    public NewLessonPlanController() {

    }

    @FXML
    private void initialize() {
        this.lessonPlanName.setText(MainApp.getCurrentCourse().getOneLessonPlan().getLessonTitle());


        cardListView.getItems().addAll(getAllCards());
        cardListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ImageView undoImg = new ImageView(new Image(NewLessonPlanController.class.getResourceAsStream("Image/undo.png")));
        undoImg.setFitHeight(20);
        undoImg.setFitWidth(20);
        undoButton.setGraphic(undoImg);

        ImageView redoImg = new ImageView(new Image(NewLessonPlanController.class.getResourceAsStream("Image/forward.png")));
        redoImg.setFitHeight(20);
        redoImg.setFitWidth(20);
        redoButton.setGraphic(redoImg);

        //Add tool tip to each button
        Tooltip tooltipUndo = new Tooltip();
        tooltipUndo.setText("Undo the action");
        undoButton.setTooltip(tooltipUndo);

        Tooltip tooltipAddEvent = new Tooltip();
        tooltipAddEvent.setText("Create a new even in your lesson plan");
        addEvent.setTooltip(tooltipAddEvent);

        Tooltip tooltipDeleteCard = new Tooltip();
        tooltipDeleteCard.setText("Delete card in event");
        deleteCard.setTooltip(tooltipDeleteCard);

        Tooltip tooltipRedo = new Tooltip();
        tooltipRedo.setText("Redo the action");
        redoButton.setTooltip(tooltipRedo);


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
        System.out.println(getAllCards().size());
        System.out.println(uniqueIdMap.keySet().size());

        this.lessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();
        this.undoRedoHandler = new CardUndoRedoHandler(lessonPlan);
    }


    private void updateFilterResults() {
        List<CardFilter> allFilters = new ArrayList<>();


        //Gender start
        List<String> selectedGenders = new ArrayList<>();
        for (Node node : genderFilterOptionsVBox.getChildren()) {
            CheckBox cBox = (CheckBox) node;
            if (cBox.isSelected()) {
                selectedGenders.add(cBox.getText());
                selectedGenders.add("N");

            }
        }
        if (!selectedGenders.isEmpty()) {
            allFilters.add(new CombineAndFilters(new GenderFilter(selectedGenders)));
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
    private void switchToPrintPreview() throws IOException {
        MainApp.setRoot("previewPage");
    }

    @FXML
    private void switchToPrintToTextPreview() throws IOException {
        MainApp.setRoot("PrintText");
    }

    @FXML
    private void handleSearch() {
        String searchText = searchBar.getText().toLowerCase().trim();

        if (searchText.isEmpty()) {
            // If the search bar is empty, display all cards
            cardListView.setItems((observableCards));
        } else {
            List<Card> matchingCards = new ArrayList<>();

            for (Card card : allCards) {
                String titleLowerCase = card.getTitle().toLowerCase();
                String shortCodeLowerCase = card.getCode().toLowerCase();
                String eventLowerCase = card.getEvent().toLowerCase();
                String categoryLowerCase = card.getCategory().toLowerCase();

                // Check if the title, short code, event, category, or any equipment contains the search text
                if (titleLowerCase.contains(searchText) ||
                        shortCodeLowerCase.contains(searchText) ||
                        eventLowerCase.contains(searchText) ||
                        categoryLowerCase.contains(searchText) ||
                        equipmentContainsSearchText(card.getEquipment(), searchText)) {
                    matchingCards.add(card);
                } else {
                    // Check if any keyword contains the search text
                    boolean keywordMatch = false;
                    for (String keyword : card.getKeywords()) {
                        if (keyword.toLowerCase().contains(searchText)) {
                            keywordMatch = true;
                            break;
                        }
                    }

                    // Add the card to the matching list if either title, short code, event, category, any equipment, or keyword matches
                    if (keywordMatch) {
                        matchingCards.add(card);
                    }
                }
            }
            cardListView.setItems(FXCollections.observableArrayList(matchingCards));
        }
    }

    // Helper method to check if any equipment contains the search text
    private boolean equipmentContainsSearchText(List<String> equipment, String searchText) {
        for (String item : equipment) {
            if (item.toLowerCase().contains(searchText)) {
                return true;
            }
        }
        return false;
    }

    /**
     * detect drag card from list view
     * @param event
     *
     */
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

    /**
     * accept drag card over
     * @param event
     */
    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleImageDropped(DragEvent event) {
        String[] uniqueIDs = event.getDragboard().getString().split("\\*");
        System.out.println("Event source: " + event.getSource());
        EventBox targetEventBox = (EventBox) event.getSource();
        List<Card> cardsToAdd = new ArrayList<>();
        for (String uniqueID : uniqueIDs) {
            Card card = CardDatabase.getCardFromUniqueID(uniqueID);
            //lessonPlan.getOneEvent().addCard(card);
            cardsToAdd.add(card);
        }
        targetEventBox.addCards(cardsToAdd);
        undoRedoHandler.saveState(lessonPlan);
    }
    private void refreshLesson() {
        // Clear the existing events
        lessonVbox.getChildren().clear();

        for (Event event : lessonPlan.getCopyOfEvents()) {
            Event newEvent = new Event(event.getEventTitle());
            EventBox newEventBox = new EventBox(newEvent);
            newEventBox.setOnDragOver(evt -> handleImageDragOver(evt));
            newEventBox.setOnDragDropped(evt -> handleImageDropped(evt));
            lessonVbox.getChildren().add(newEventBox);
        }
    }

    private List<CardImageView> selectedNodes = new ArrayList<>();

    @FXML
    private void handleAddEvent(ActionEvent event) {
        // You can prompt the user to enter the event name using a dialog or text input field
        TextInputDialog dialog = new TextInputDialog("Event Name");
        dialog.setTitle("New Event");
        dialog.setHeaderText("Enter the name for the new event:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(eventName -> {
            Event newEvent = new Event(eventName);
            lessonPlan.addEvent(newEvent);
            EventBox newEventBox = new EventBox(newEvent);
            newEventBox.setOnDragOver(evt -> handleImageDragOver(evt));
            newEventBox.setOnDragDropped(evt -> handleImageDropped(evt));
            lessonVbox.getChildren().add(newEventBox);
            undoRedoHandler.saveState(lessonPlan);
        });
    }

    @FXML
    private void actionDeleteCard() {
        for (Node node :lessonVbox.getChildren()) {
            if (node instanceof EventBox) {
                EventBox eventBox = (EventBox) node;
                eventBox.deleteSelectedCards();
            }
        }
        undoRedoHandler.saveState(lessonPlan);


//        } else {
//            new Alert(Alert.AlertType.WARNING, "Select a card to delete").show();
//        }
    }


    @FXML
    private void toggleSelection(ImageView node) {
        if (selectedNodes.contains(node)) {
            node.getStyleClass().remove("selected");
            selectedNodes.remove(node);
        } else {
            node.getStyleClass().add("selected");
            selectedNodes.add((CardImageView) node);
        }
    }


    @FXML
    private void menuActionOpen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Course (*.gymCourse)", "*.gymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonVbox.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        if (chosenFile != null) {
            MainApp.openCurrentCourseFromFile(chosenFile); //make a try catch
            //TODO: This is wrong... maybe don't let people open courses from the
            // lesson editing page?
            lessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();
            refreshLesson();
        }
    }

    @FXML
    private void menuActionSave(ActionEvent event) {
        if (MainApp.getCurrentCourse() == null) {
            menuActionSaveAs(event);
        } else {
            saveCurrentCourseToFile(MainApp.getCurrentCourseFile());
        }
    }

    @FXML
    private void menuActionSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Course (*.gymCourse)", "*.gymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonVbox.getScene().getWindow();
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
    @FXML
    private void handleUndoButton(ActionEvent event) {
        undoRedoHandler.undo(lessonPlan);
        undoRedoHandler.saveState(lessonPlan);
        refreshLesson();
    }

    @FXML
    private void handleRedoButton(ActionEvent event) {
        undoRedoHandler.redo(lessonPlan);
        undoRedoHandler.saveState(lessonPlan);
        refreshLesson();
    }
}



