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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
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
    private Button deleteEvent;
    @FXML
    private VBox lessonVbox;
    @FXML
    public Label lessonPlanName;
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

    @FXML
    private  Button btnHelp;


    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up initial data, UI components, and listeners.
     */
    @FXML
    private void initialize() {
        if (!MainApp.getCurrentCourse().getOneLessonPlan().isOpenFromList()) {
            this.lessonPlanName.setText(MainApp.getCurrentCourse().getOneLessonPlan().getLessonTitle());

            this.lessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();
        } else {
            lessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();
            addCardsFromOpen();
        }
        cardListView.getItems().addAll(getAllCards());
        cardListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ImageView undoImg = new ImageView(new Image(NewLessonPlanController.class.getResourceAsStream("Image/undo.png")));
        undoImg.setFitHeight(20);
        undoImg.setFitWidth(20);
        deleteEvent.setGraphic(undoImg);

        //  ImageView redoImg = new ImageView(new Image(NewLessonPlanController.class.getResourceAsStream("Image/forward.png")));
        // redoImg.setFitHeight(20);
        // redoImg.setFitWidth(20);
        // redoButton.setGraphic(redoImg);

        //Add tool tip to each button
        Tooltip tooltipUndo = new Tooltip();
        tooltipUndo.setText("Undo the action");
        deleteEvent.setTooltip(tooltipUndo);

        Tooltip tooltipAddEvent = new Tooltip();
        tooltipAddEvent.setText("Create a new event in your lesson plan");
        addEvent.setTooltip(tooltipAddEvent);

        Tooltip tooltipDeleteCard = new Tooltip();
        tooltipDeleteCard.setText("Delete the selected card in the event");
        deleteCard.setTooltip(tooltipDeleteCard);

        //Tooltip tooltipRedo = new Tooltip();
        //tooltipRedo.setText("Redo the action");
        // redoButton.setTooltip(tooltipRedo);


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

    }

    /**
     * Updates the filtered results based on the selected filters.
     */
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

    /**
     * Handles the search functionality in the lesson plan.
     */
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

    /**
     * Checks if any equipment contains the search text.
     *
     * @param equipment   The list of equipment to be checked.
     * @param searchText  The search text to be checked in equipment.
     * @return True if the search text is found in any equipment; otherwise, false.
     */
    private boolean equipmentContainsSearchText(List<String> equipment, String searchText) {
        for (String item : equipment) {
            if (item.toLowerCase().contains(searchText)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detects drag card from list view.
     *
     * @param event The MouseEvent for drag detection.
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
     *
     * @param event
     */
    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    /**
     * Handles the dropping of a dragged card.
     *
     * @param event The DragEvent when a card is dropped.
     */
    @FXML
    void handleImageDropped(DragEvent event) {
        String[] uniqueIDs = event.getDragboard().getString().split("\\*");
        System.out.println("Event source: " + event.getSource());
        EventBox targetEventBox = (EventBox) event.getSource();
        List<Card> cardsToAdd = new ArrayList<>();
        for (String uniqueID : uniqueIDs) {
            Card card = CardDatabase.getCardFromUniqueID(uniqueID);
            lessonPlan.getOneEvent().addCard(card);
            cardsToAdd.add(card);
        }
        targetEventBox.addCards(cardsToAdd);

    }

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
        });
    }

    @FXML
    private void actionDeleteCard() {
        for (Node node : lessonVbox.getChildren()) {
            if (node instanceof EventBox) {
                EventBox eventBox = (EventBox) node;
                eventBox.deleteSelectedCards();
            }
        }
    }

    /**
     * Adds cards from an opened lesson plan.
     */
    public void addCardsFromOpen() {
        this.lessonPlanName.setText(MainApp.getCurrentCourse().getOneLessonPlan().getLessonTitle());
        lessonVbox.getChildren().clear();
        System.out.println(lessonPlan.getEventList());
        for (Event event : lessonPlan.getEventList()) {
            EventBox newEventBox = new EventBox(event);
            newEventBox.setOnDragOver(evt -> handleImageDragOver(evt));
            newEventBox.setOnDragDropped(evt -> handleImageDropped(evt));
            lessonVbox.getChildren().add(newEventBox);
        }
    }

    /**
     * Opens a file dialog to select and open a lesson plan file.
     *
     * @param event The ActionEvent triggering the method.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    private void menuActionOpen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lesson Plan File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Plan (*.gymPlan)", "*.gymPlan");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonVbox.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        if (chosenFile != null) {
            MainApp.openCurrentCourseFromFile(chosenFile); //make a try catch
            lessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();
            addCardsFromOpen();
        }
    }

    /**
     * Saves the current lesson plan.
     *
     * @param event The ActionEvent triggering the method.
     */
    @FXML
    private void menuActionSave(ActionEvent event) {
        if (MainApp.getCurrentCourse() == null) {
            menuActionSaveAs(event);
        } else {
            saveCurrentCourseToFile(MainApp.getCurrentCourseFile());
        }
    }

    /**
     * Saves the current lesson plan to a new file.
     *
     * @param event The ActionEvent triggering the method.
     */
    @FXML
    private void menuActionSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lesson Plan File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gymnastics Plan (*.gymPlan)", "*.gymPlan");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonVbox.getScene().getWindow();
        File chosenFile = fileChooser.showSaveDialog(mainWindow);
        saveCurrentCourseToFile(chosenFile);
    }

    /**
     * Saves the current course to the specified file.
     *
     * @param chosenFile The file to which the course will be saved.
     */
    private void saveCurrentCourseToFile(File chosenFile) {
        if (chosenFile != null) {
            try {
                MainApp.saveCurrentCourseToFile(chosenFile);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving movie log file: " + chosenFile).show();
            }
        }
    }

    /**
     * Deletes the selected event(s) from the lesson plan.
     */
    @FXML
    private void deleteSelectedEvent() {
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : lessonVbox.getChildren()) {
            if (node instanceof EventBox) {
                EventBox eventBox = (EventBox) node;
                if (eventBox.isSelected()) {
                    nodesToRemove.add(eventBox);
                }
            }
        }

        if (!nodesToRemove.isEmpty()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Event(s)");
            confirmationAlert.setHeaderText("Are you sure you want to delete the selected event(s)?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create a copy of the list of children before iterating and modifying
                List<Node> childrenCopy = new ArrayList<>(lessonVbox.getChildren());

                for (Node node : childrenCopy) {
                    if (nodesToRemove.contains(node)) {
                        lessonPlan.removeEvent(((EventBox) node).getEvent());
                        lessonVbox.getChildren().remove(node);
                    }
                }
            }
        }
    }

}