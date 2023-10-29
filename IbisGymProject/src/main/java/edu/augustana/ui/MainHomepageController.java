package edu.augustana.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainHomepageController {

    // for mainHomepage.fxml
    @FXML private Button allCardsButton;
    @FXML private Button deletedButton;
    @FXML private Button favoritesButton;
    @FXML private Button folderButton;
    @FXML private Button newLessonButton;
    @FXML private Button qaButton;
    @FXML private Button printButton;
    @FXML private Button settingsButton;
    @FXML private TextField searchBar;

    //for newLessonPopup.fxml
    @FXML private Button doneMakingLesson;
    @FXML private CheckBox folderCheckbox;
    @FXML private TextField folderText;
    @FXML private TextField newLessonText;

    @FXML private edu.augustana.ui.newLessonPlanController newLessonPlanController = new newLessonPlanController();

    @FXML
    private void initialize() {
        //newLessonText.setOnKeyTyped(e -> handleQuestTextChange(e));
    }

    @FXML
    private void openFolderView() throws IOException {
        MainApp.setRoot("folderPage");
    }

    @FXML
    private void backToHome() throws IOException {
        MainApp.setRoot("mainHomepage");
    }

    @FXML
    private void handleCreateNewLesson() throws IOException {

        // Load the FXML content for the popup
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newLessonPopup.fxml"));
        loader.setController(newLessonPlanController);
        Parent popupContent;
        try {
            popupContent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Get the stage from any node in your scene
        Stage stage = (Stage) newLessonButton.getScene().getWindow();
        // Create a Popup instance and set its content
        Popup popup = new Popup();
        popup.getContent().add(popupContent);

        // Set the position of the popup relative to the stage
        popup.setX(stage.getX()+50); // Set your desired X position
        popup.setY(stage.getY()+50); // Set your desired Y position



        // Show the popup
        popup.show(stage);



        Button okayButton = (Button) popupContent.lookup("#okayDoneWithNewLessonPlanButton");
        okayButton.setOnAction(event -> {
            setNewLessonButton();
            popup.hide();
            newLessonPlanController.setLessonPlanName("Hello");



            // Add any additional logic you want to execute when the popup is closed.
        });

        // Close the popup when the "Done" button in the popup is clicked
        Button doneButton = (Button) popupContent.lookup("#quitPopUpButton");
        doneButton.setOnAction(event -> {
            popup.hide();

            // Add any additional logic you want to execute when the popup is closed.
        });
    }


    @FXML
    private void setNewLessonButton() {
        MainApp.switchToNewLessonCreationPage();
    }

    @FXML
    private void openAllCardsView() throws IOException {
        MainApp.setRoot("allCardsView");
    }

    @FXML
    private void printScene() {
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