package edu.augustana.data;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //Add a list of cards

    private List<Card> lessonPlanCards = new ArrayList<>();
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

    //public Map<String,List<Card>> getCardListsByEvent(){
        // would return a map like:
        //  { "floor" : [ FloorCard1, FloorCard2 ],
        //   "vault" : [ VaultCards... ],
        //
    //}
}
