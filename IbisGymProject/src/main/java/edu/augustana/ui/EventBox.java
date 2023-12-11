package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class EventBox extends VBox {

    private final Label eventLabel;
    private final FlowPane eventFlowPane;
    private Event event;

    private boolean selected;

    /**
     * Constructs an EventBox with the specified event.
     *
     * @param event The Event object associated with this box.
     */
    public EventBox(Event event) {
        super();
        this.event = event;
        this.selected = false;
        this.eventLabel = new Label(event.getEventTitle());
        this.eventFlowPane = new FlowPane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        eventFlowPane.setBackground(background);
        eventFlowPane.setPadding(new Insets(10));
        eventFlowPane.setMaxWidth(955);
        ImageView dragImageHereIcon = new ImageView(new Image(EventBox.class.getResourceAsStream("Image/Drag card here.png")));
        dragImageHereIcon.setFitHeight(120);
        dragImageHereIcon.setFitWidth(180);
        eventFlowPane.getChildren().add(dragImageHereIcon);
        this.getChildren().addAll(eventLabel, eventFlowPane);
        setId("event-box");
        eventLabel.getStyleClass().add("event-title");

        setOnMouseClicked(this::toggleSelection);
        addCards(event.getCards());
    }

    /**
     * Checks if the EventBox is selected.
     *
     * @return True if the EventBox is selected; otherwise, false.
     */
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0);
            dropShadow.setOffsetX(5.0);
            dropShadow.setOffsetY(5.0);
            setEffect(dropShadow);
        }else{
            setEffect(null);
        }
    }


    /**
     * Toggles the selection state of the EventBox based on mouse click.
     *
     * @param event The MouseEvent triggering the selection toggle.
     */
    public void toggleSelection(MouseEvent event) {
        setSelected(!isSelected());
    }

    /**
     * Adds cards to the EventBox, associating them with the current event.
     *
     * @param cards The list of Card objects to add to the EventBox.
     */
    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            event.addCard(card);

            Image image = card.getImage();
            CardImageView cardImageView = new CardImageView(image, card);
            cardImageView.setImage(image);
            cardImageView.setFitWidth(180);
            cardImageView.setFitHeight(120);
            // Set the Card as user data for later retrieval
            cardImageView.setUserData(card);

            eventFlowPane.setHgap(5);
            eventFlowPane.setVgap(5);
            eventFlowPane.getChildren().add(cardImageView);
        }

    }

    public Event getEvent() {
        return event;
    }

    /**
     * Deletes the selected cards from the EventBox and the associated event.
     */
    public void deleteSelectedCards() {
        for (Node node : new ArrayList<>(eventFlowPane.getChildren())) {
            if (node instanceof CardImageView) {
                CardImageView cardImageView = (CardImageView) node;
                if (cardImageView.isSelected()) {
                    eventFlowPane.getChildren().remove(cardImageView);
                    event.removeCard(cardImageView.getMyCard());
                }
            }
        }
    }
}