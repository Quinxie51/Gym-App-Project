package edu.augustana.ui;

import edu.augustana.data.Card;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class CardImageView extends ImageView {
    private boolean selected = false;
    private final Card myCard;
    private boolean isZoomed = false;

    /**
     * Constructs a CardImageView with the specified image and card.
     *
     * @param image - The image to be displayed in the ImageView.
     * @param myCard - The associated Card object represented by this view.
     */
    public CardImageView(Image image, Card myCard) {
        super(image);
        this.myCard = myCard;

        this.setOnMouseReleased(event -> {
            if (event.getClickCount() == 1) {
                handleCardActivation();
            }
            if (event.getClickCount() == 2) {
                handleCardZoom();
            }
        });
    }

    /**
     * Toggles the selection state of the card and adjusts its visual effects accordingly.
     */
    private void handleCardActivation() {
        selected = !selected;
        System.out.println("Selected =" + selected);
        if (selected && !isZoomed) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.3);
            this.setEffect(colorAdjust);
        } else {
            setStyle(null);
            setEffect(null);
        }
    }

    /**
     * Toggles the zoom state of the card and applies zoom in or zoom out effects.
     */
    private void handleCardZoom() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), this);
        if (!isZoomed) {
            // If the card is not zoomed in, zoom in
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0);
            dropShadow.setOffsetX(5.0);
            dropShadow.setOffsetY(5.0);
            setEffect(dropShadow);
            scaleTransition.setToX(1.75);
            scaleTransition.setToY(1.75);
            // Set a higher viewOrder when zoomed in
            setViewOrder(-1);
            isZoomed = true;
        } else {
            // If the card is already zoomed in, zoom out
            setEffect(null);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            // Restore the default viewOrder
            setViewOrder(0);
            isZoomed = false;
        }
        scaleTransition.play();
    }


    public Card getMyCard() {
        return myCard;
    }

    /**
     * Checks if the card is selected.
     *
     * @return True if the card is selected; otherwise, false.
     */
    public boolean isSelected() {
        return selected;
    }
}
