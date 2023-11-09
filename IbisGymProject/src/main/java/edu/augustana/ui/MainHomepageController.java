package edu.augustana.ui;

import java.io.IOException;

import edu.augustana.data.Course;
import edu.augustana.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Optional;

public class MainHomepageController {

    // for mainHomepage.fxml
    @FXML
    private Button allCardsButton;
    @FXML
    private Button deletedButton;
    @FXML
    private Button favoritesButton;
    @FXML
    private Button folderButton;
    @FXML
    private Button newLessonButton;
    @FXML
    private Button qaButton;
    @FXML
    private Button printButton;
    @FXML
    private Button settingsButton;

    @FXML
    Label newLessonPlanName = new Label();

    //for newLessonPopup.fxml
    @FXML
    private Button doneMakingLesson;
    @FXML
    private CheckBox folderCheckbox;
    @FXML
    private TextField folderText;
    @FXML
    private TextField newLessonText;

    @FXML
    private edu.augustana.ui.newLessonPlanController newLessonPlanController = new newLessonPlanController();

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

        //Stack overflow code
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Lesson");
        dialog.setHeaderText("New lesson name:");
        // Handle the result
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String lessonTitle = result.get();
            MainApp.getCurrentCourse().getOneLessonPlan().setLessonTitle(lessonTitle);
            MainApp.switchToNewLessonCreationPage();
        }
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
