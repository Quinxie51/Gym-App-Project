package edu.augustana.ui;

import edu.augustana.data.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class CardImageView extends ImageView {
    private Card myCard;

    public CardImageView(Image image, Card myCard) {
        super(image);
        this.myCard = myCard;
    }

    // Method to set the active mode for a selected card
    private void setActiveMode() {
        setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
    }

    // Method to reset the style for all cards
    private void resetStyles(List<CardImageView> selectedNodes) {
        for (CardImageView cardImageView : selectedNodes) {
            cardImageView.setStyle(null); // Reset style to default
        }
    }

    // Modify your selection logic to call setActiveMode for the selected card
    public void handleCardSelection(List<CardImageView> selectedNodes) {
        resetStyles(selectedNodes); // Reset styles for all cards
        setActiveMode(); // Set active mode for the selected card
        // Additional logic for card selection...
    }

    public Card getMyCard() {
        return myCard;
    }
}
