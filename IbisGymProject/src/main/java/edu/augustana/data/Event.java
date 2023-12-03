package edu.augustana.data;

import java.util.ArrayList;
import java.util.List;

public class Event {
    //Add a list of cards

    private String eventTitle;

    private List<String> cardIdList = new ArrayList<>();
    private static List<Card> cardList = new ArrayList<>();

    public Event(String eventTitle) {
        this.eventTitle = eventTitle;
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
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
