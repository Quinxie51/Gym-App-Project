package edu.augustana;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;





public class newLessonPlanController {
    //datafields


     @FXML public Label lessonPlanName;

    public newLessonPlanController(){

    }
    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("mainHomepage");
    }
    @FXML
    private ImageView imageView;





    public void setLessonPlanName(String text) {
        lessonPlanName.setText(text);
    }
    public void handleDrop(DragEvent event){
        //List<File> files = event.getDragboard().getFiles();
        //Image img = new Image(new FileInputStream(files.get(0)));
        //imageView.setImage(img);
        }
    public void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

}