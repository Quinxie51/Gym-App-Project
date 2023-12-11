package edu.augustana.data;

import edu.augustana.ui.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents an event with a list of associated cards.
 */
public class Event implements Cloneable {

    //Add a list of cards
    private String eventName;
    private List<String> cardIdList = new ArrayList<>();

    /**
     * Constructs an Event object with the specified event name.
     *
     * @param eventName The name of the event.
     */
    private String eventNameStyle; // Store the style information

    public Event(String eventName, String eventNameStyle) {
        this.eventName = eventName;
        this.eventNameStyle = eventNameStyle;
    }


    public String getEventTitle() {

        return eventName;

    }


    public void removeCard(Card card) {
        cardIdList.remove(card.getUniqueID());
    }

    public void addCard(Card card) {
        cardIdList.add(card.getUniqueID());
    }

    public List<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (String id : cardIdList) {
            cards.add(CardDatabase.getCardFromUniqueID(id));
        }
        return cards;
    }

    @Override
    public Event clone() {
        try {
            Event clone = (Event) super.clone();
            clone.cardIdList = new ArrayList<>(this.cardIdList);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "{[}" + eventName + ": " +
                 cardIdList +
                '}';
    }
}
