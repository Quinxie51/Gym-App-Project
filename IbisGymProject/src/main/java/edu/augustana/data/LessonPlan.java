package edu.augustana.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LessonPlan {
    //Add a list of cards

    private String lessonTitle;
    private Event newEvent;

    private List<String> cardIdList = new ArrayList<>();
    private static List<Card> cardList = new ArrayList<>();

    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public LessonPlan(LessonPlan otherLessonPlan) {
        // Copy the values from the otherLessonPlan to initialize the new LessonPlan object
        this.lessonTitle = otherLessonPlan.lessonTitle;
        this.cardIdList = new ArrayList<>(otherLessonPlan.cardIdList);
    }


    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
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

    public void addEvent(Event newEvent) {
        cardList.remove(newEvent);

    }
}

    //public Map<String,List<Card>> getCardListsByEvent(){
        // would return a map like:
        //  { "floor" : [ FloorCard1, FloorCard2 ],
        //   "vault" : [ VaultCards... ],
        //
    //}
}
