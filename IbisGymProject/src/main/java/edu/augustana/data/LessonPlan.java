package edu.augustana.data;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //Add a list of cards

    private List<Card> lessonPlanCards = new ArrayList<>();
    private String lessonTitle;
    private List<String> cardIdList = new ArrayList<>();

    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }


    public void addCard(Card card) {
        cardIdList.add(card.getUniqueID());

    }

    //public Map<String,List<Card>> getCardListsByEvent(){
        // would return a map like:
        //  { "floor" : [ FloorCard1, FloorCard2 ],
        //   "vault" : [ VaultCards... ],
        //
    //}
}
