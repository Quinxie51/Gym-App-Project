package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class EventBox extends VBox {

    private final Label eventLabel;
    private final FlowPane eventFlowPane;
    private Event event;


    public EventBox(Event event) {
        super();
        this.eventLabel = new Label(event.getEventTitle());
        this.eventFlowPane = new FlowPane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        eventFlowPane.setBackground(background);

        this.getChildren().addAll(eventLabel, eventFlowPane);
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            event.addCard(card);
        }
    }

    }



