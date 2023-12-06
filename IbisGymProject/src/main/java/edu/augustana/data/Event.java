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

public class Event {
    //Add a list of cards
    private String eventName;

    private List<String> cardIdList = new ArrayList<>();

    public Event(String eventName) {
        this.eventName = eventName;
    }


    public String getEventTitle() {
        return eventName;
    }

    public void setEventTitle(String eventTitle) {
        this.eventName = eventTitle;
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



    //public Map<String,List<Card>> getCardListsByEvent(){
    // would return a map like:
    //  { "floor" : [ FloorCard1, FloorCard2 ],
    //   "vault" : [ VaultCards... ],
    //
    //}
}
