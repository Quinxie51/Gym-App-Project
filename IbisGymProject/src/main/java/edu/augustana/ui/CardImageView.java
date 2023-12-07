package edu.augustana.ui;

import edu.augustana.data.Card;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CardImageView extends ImageView {
    private Card myCard;

    public CardImageView(Image image, Card myCard) {
        super(image);
        this.myCard = myCard;

        setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                handleCardActivation();
            }
        });
    }

    // Method to set the active mode for a selected card
    private void setActiveMode() {
        setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
    }

    // Method to reset the style for all cards
    private void resetStyles() {
        setStyle(null); // Reset style to default
    }

    // Method to handle card activation
    private void handleCardActivation() {
        if (getStyle() == null) {
            setActiveMode();
        } else {
            resetStyles();
        }
    }

    // Method to handle card zoom in and out


    public Card getMyCard() {
        return myCard;
    }
}
