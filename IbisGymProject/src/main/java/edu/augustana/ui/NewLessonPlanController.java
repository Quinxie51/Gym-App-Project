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
    private LessonPlan lessonPlan;
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
    private PrintManager vboxPage;
    @FXML
    private ListView<Card> cardListView;
    @FXML
    private Button deleteCard;
    @FXML
    private Button addEvent;
    @FXML
    private Event eventSection;

    private CardUndoRedoHandler undoRedoHandler;


    public NewLessonPlanController() {

    }

    @FXML
    private void initialize() {
        this.lessonPlanName.setText(MainApp.getCurrentCourse().getOneLessonPlan().getLessonTitle());
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        //lessonFlowPane = new LimitedFlowPane(8);
        lessonFlowPane.setBackground(background);
        this.vboxPage = new PrintManager(lessonFlowPane);


        final Tooltip tooltipAddEvent = new Tooltip();
        tooltipAddEvent.setText("Create a new even in your lesson plan");
        addEvent.setTooltip(tooltipAddEvent);

        final Tooltip tooltipDeleteCard = new Tooltip();
        tooltipDeleteCard.setText("Delete card in event");
        deleteCard.setTooltip(tooltipAddEvent);
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
        System.out.println(getAllCards().size());
        System.out.println(uniqueIdMap.keySet().size());

        this.lessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

        undoRedoHandler = new CardUndoRedoHandler();
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
        for (CardImageView selectedNode : selectedNodes) {
            undoRedoHandler.saveState(selectedNode.getMyCard());
        }
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
            CardImageView cardImageView = new CardImageView(image, card);
            cardImageView.setImage(image);
            cardImageView.setFitWidth(180);
            cardImageView.setFitHeight(120);
            cardImageView.setOnMouseClicked(event -> {
                toggleSelection(cardImageView);
                event.consume();
            });
            // Set the Card as user data for later retrieval
            cardImageView.setUserData(card);
            lessonFlowPane.setHgap(10); // should be set in scenebuilder
            lessonFlowPane.setVgap(10);
            lessonFlowPane.getChildren().add(cardImageView);
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
            MainApp.getCurrentCourse().getOneLessonPlan().addEvent(newEvent);
            refreshLessonView();  // Refresh the UI to display the new event
        });
    }

    @FXML
    private void actionDeleteCard() {
        // Create a copy of the selectedNodes list
        List<CardImageView> nodesToDelete = new ArrayList<>(selectedNodes);

        if (!nodesToDelete.isEmpty()) {
            Iterator<CardImageView> iterator = selectedNodes.iterator();

            while (iterator.hasNext()) {
                CardImageView selectedNode = iterator.next();
                String uniqueID = selectedNode.getMyCard().getUniqueID();
                Card cardToDelete = CardDatabase.getCardFromUniqueID(uniqueID);

                // Remove the node from the FlowPane
                lessonFlowPane.getChildren().remove(selectedNode);

                // Remove the card from the lesson plan or any other data structure
                this.lessonPlan.removeCard(cardToDelete);

                // Remove the node from the selectedNodes list using the iterator
                iterator.remove();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Select a card to delete").show();
        }
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
        Window mainWindow = lessonFlowPane.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        if (chosenFile != null) {
            MainApp.openCurrentCourseFromFile(chosenFile); //make a try catch
            refreshLessonView();
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

    @FXML
    private void handleUndoButton() {
        System.out.println("Undo button clicked");
        Card lastAddedCard = getLastAddedCard();

        if (lastAddedCard != null) {
            undoRedoHandler.undo(lastAddedCard);
            refreshLessonView();
        } else {
            new Alert(Alert.AlertType.WARNING, "No card to undo").show();
        }
    }

    @FXML
    private void handleRedoButton() {
        System.out.println("Redo button clicked");
        Card lastAddedCard = getLastAddedCard();

        if (lastAddedCard != null) {
            undoRedoHandler.redo(lastAddedCard);
            refreshLessonView();
        } else {
            new Alert(Alert.AlertType.WARNING, "No card to redo").show();
        }
    }
    private Card getLastAddedCard() {
        List<Card> lessonPlanCards = MainApp.getCurrentCourse().getOneLessonPlan().getCards();

        if (!lessonPlanCards.isEmpty()) {
            return lessonPlanCards.get(lessonPlanCards.size() - 1);
        }

        return null;
    }
}



