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
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

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

    @FXML private edu.augustana.ui.newLessonPlanController newLessonPlanController;

    @FXML
    private void initialize() {

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
        TextInputDialog dialog = new TextInputDialog();  // create an instance

        dialog.setTitle("Title");
// other formatting etc

        Optional<String> result = dialog.showAndWait();
// this shows the dialog, waits until it is closed, and stores the result


// if the result is present (i.e. if a string was entered) call doSomething() on it
        result.ifPresent(string -> {
        newLessonPlanController.setLessonPlanName("moose");
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
