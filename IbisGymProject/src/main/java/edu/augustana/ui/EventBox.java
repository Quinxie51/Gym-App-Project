package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class EventBox extends VBox {

    private final Label eventLabel;
    private final FlowPane eventFlowPane;
    private Event event;


    public EventBox(Event event) {
        super();
        this.event = event;
        this.eventLabel = new Label(event.getEventTitle());
        this.eventFlowPane = new FlowPane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        eventFlowPane.setBackground(background);
        eventFlowPane.setPadding(new Insets(10));
        ImageView dragImageHereIcon = new ImageView(new Image(EventBox.class.getResourceAsStream("Image/Drag card here.png")));
        dragImageHereIcon.setFitHeight(120);
        dragImageHereIcon.setFitWidth(180);
        eventFlowPane.getChildren().add(dragImageHereIcon);
        this.getChildren().addAll(eventLabel, eventFlowPane);
        addCards(event.getCards());
    }


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

    public void deleteSelectedCards(){
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




