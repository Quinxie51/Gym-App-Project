package edu.augustana;

import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadImagesFromCSV extends MainApp {

    private static final String FILE_PATH = "src/main/resources/edu/augustana/DEMO1.csv";

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Images from CSV File");
        primaryStage.setScene(scene);

        List<String> imagePaths = getImagePathsFromCSV(FILE_PATH);
        List<ImageView> imageViews = new ArrayList<>();

        for (int i = 0; i < imagePaths.size(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(50 + i * 120);
            imageView.setLayoutY(50);
            root.getChildren().add(imageView);
            imageViews.add(imageView);
        }

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                for (int i = 0; i < imagePaths.size(); i++) {
                    String imagePath = imagePaths.get(i);
                    Image image = new Image(imagePath, true);
                    ImageView imageView = imageViews.get(i);
                    imageView.setImage(image);
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        primaryStage.show();
    }

    private List<String> getImagePathsFromCSV(String filePath) {
        List<String> imagePaths = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    // Assuming the image paths are in the first column of the CSV file
                    String imagePath = data[0].trim();
                    imagePaths.add(imagePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePaths;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
