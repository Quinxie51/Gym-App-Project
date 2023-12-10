package edu.augustana.ui;
import com.opencsv.exceptions.CsvValidationException;
import edu.augustana.data.CardDatabase;
import edu.augustana.data.Course;
import edu.augustana.data.LessonPlan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class MainApp extends Application {
    private static Scene scene;

    private static Course currentCourse = new Course();
    private static File currentCourseFile = null;

    private static LessonPlan currentLessonPlan;

    //HELLO THUNDERDOME

    /**
     * This method is called when the JavaFX application is launched.
     * It initializes the application, loads cards from a CSV file,
     * and sets up the main scene.
     *
     * @param stage The primary stage for this application.
     * @throws IOException            if an error occurs while loading FXML or other resources.
     * @throws CsvValidationException if an error occurs during CSV validation.
     */
    @Override
    public void start(Stage stage) throws IOException, CsvValidationException {
        CardDatabase.addCardsFromCSVFile();

        scene = new Scene(loadFXML("mainHomepage"), 640, 480);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml The name of the FXML file (without the ".fxml" extension).
     * @throws IOException if an error occurs while loading the FXML file.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static void switchToView(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxmlFileName));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            System.err.println("Can't find FXML file " + fxmlFileName);
            ex.printStackTrace();
        }
    }
    /**
     * Switches the scene to the library view.
     */
    public static void switchToLibrary() {
        switchToView("myLibrary.fxml");
    }

    /**
     * Switches the scene to the new lesson creation page.
     */
    public static void switchToNewLessonCreationPage() {
        System.out.println("switchToNewLessonCreationPage");
        switchToView("newLessonCreationPage.fxml");
    }

    /**
     * Gets the current scene.
     *
     * @return The current scene.
     */
    public static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /**
     * Opens the specified file and loads the associated course.
     *
     * @param chosenFile The file to open and load the course from.
     * @throws IOException if an error occurs while reading the file or loading the course.
     */
    public static void openCurrentCourseFromFile(File chosenFile) throws IOException {
        currentCourseFile = chosenFile;
        currentCourse = Course.loadFromFile(chosenFile);
    }
    /**
     * Saves the current course to the specified file.
     *
     * @param chosenFile The file to save the current course to.
     * @throws IOException if an error occurs while writing to the file.
     */
    public static void saveCurrentCourseToFile(File chosenFile) throws IOException {
        currentCourse.saveToFile(chosenFile);
        currentCourseFile = chosenFile;
    }
    /**
     * Gets the current course.
     *
     * @return The current course.
     */
    public static Course getCurrentCourse() {
        return currentCourse;
    }
    /**
     * Gets the file associated with the current course.
     *
     * @return The file associated with the current course.
     */
    public static File getCurrentCourseFile() {
        return currentCourseFile;
    }
    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {

        System.out.println("Main is running!");
        launch();
    }

}
