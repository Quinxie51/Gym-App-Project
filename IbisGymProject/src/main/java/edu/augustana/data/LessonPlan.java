package edu.augustana.data;

import edu.augustana.ui.CardUndoRedoHandler;

import edu.augustana.ui.LessonPlanState;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //Add a list of cards

    private LessonPlan currentLessonPlan = this;
    private String lessonTitle;
    private List<Card> cardList = new ArrayList<>();
    private Event newEvent;
    private List<String> cardIdList = new ArrayList<>();
    private CardUndoRedoHandler undoRedoHandler;

    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
        this.undoRedoHandler = new CardUndoRedoHandler();
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

    public void restoreState(LessonPlanState state) {
        // Clear existing cards and add cards from the state
        clearCards();
        addCards(state.getCards());
    }
    public void clearCards() {
        cardList.clear();
        cardIdList.clear();
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            if (!cardIdList.contains(card.getUniqueID())) {
                cardIdList.add(card.getUniqueID());
                cardList.add(card);
            }
        }
    }
}
