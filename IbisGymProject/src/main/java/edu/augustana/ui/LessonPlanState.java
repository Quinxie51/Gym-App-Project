package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.LessonPlan;

import java.util.ArrayList;
import java.util.List;

public class LessonPlanState {

    private final List<Card> cards;

    public LessonPlanState(LessonPlan lessonPlan) {
        this.cards = new ArrayList<>(lessonPlan.getCards());
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        List<String> cardCodes = new ArrayList<>();
        for (Card c : cards) {
            cardCodes.add(c.getCode());
        }

        return "LessonPlanState{" +
                "cards=" + cardCodes +
                '}';
    }
}

