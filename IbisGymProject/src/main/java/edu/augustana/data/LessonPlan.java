package edu.augustana.data;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //Add a list of cards

    private String lessonTitle;

    private static List<String> cardIdList = new ArrayList<>();
    private static List<Card> cardList = new ArrayList<>();

    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }


    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public void removeCard(Card card) {
        cardIdList.add(card.getUniqueID());
        cardList.remove(card);
    }

    public static void addCard(Card card) {
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
