package edu.augustana.ui;

import edu.augustana.data.Card;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class CardImageView extends ImageView {

    private BooleanProperty selected = new SimpleBooleanProperty(false);

    private Card myCard;
    private boolean isZoomed = false;



    public CardImageView(Image image, Card myCard) {
        super(image);
        this.myCard = myCard;



        setOnMouseEntered(event -> {
            handleCardZoom();
        });

        setOnMouseExited(event -> {
            if (isZoomed) {
                handleCardZoom(); // Zoom out when the mouse exits
            }
        });
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                handleCardActivation();
            }
        });
}


    private void handleCardActivation() {
        if (selected.get()) {
            setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
        } else {
            setStyle(null);
        }
    }

    // Method to handle card zoom in and out
    private void handleCardZoom() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), this);

        if (!isZoomed) {
            // If the card is not zoomed in, zoom in


            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0);
            dropShadow.setOffsetX(5.0);
            dropShadow.setOffsetY(5.0);

            setEffect(dropShadow);
            scaleTransition.setToX(2.5);
            scaleTransition.setToY(2.5);

            // Set a lower viewOrder when zoomed in
            setViewOrder(-1.0);

            isZoomed = true;
        } else {
            // If the card is already zoomed in, zoom out
            setEffect(null);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);

            
            setViewOrder(0);

            isZoomed = false;
        }

        scaleTransition.play();
    }


    public Card getMyCard() {
        return myCard;
    }
}
