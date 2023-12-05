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

    @FXML
    private MenuBar eventype;
    @FXML
    private FlowPane eventFlowPane;
    @FXML
    private String eventName;

    private List<String> cardIdList = new ArrayList<>();
    private static List<Card> cardList = new ArrayList<>();


    public Event(String eventName) {
        this.eventName = eventName;
        initializeFlowPane();
    }
    private void initializeFlowPane() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        eventFlowPane.setBackground(background);
        eventFlowPane = new FlowPane();
    }

    public String getEventTitle() {
        return eventName;
    }

    public void setEventTitle(String eventTitle) {
        this.eventName = eventTitle;
    }

    public void removeCard(Card card) {
        cardIdList.remove(card.getUniqueID());
        cardList.remove(card);

    }

    public void addCard(Card card) {
        cardIdList.add(card.getUniqueID());
        cardList.add(card);

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
