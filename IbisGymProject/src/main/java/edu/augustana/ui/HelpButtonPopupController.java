package edu.augustana.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class HelpButtonPopupController {


    private Popup popup;

    public void setPopup(Popup popupSent) {
        this.popup = popupSent;
    }
    @FXML
    public void onDoneButtonClicked (){
        if (popup != null) {
            popup.hide();
        }
    }

}
