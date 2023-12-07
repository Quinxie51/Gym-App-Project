package edu.augustana.data;

import edu.augustana.ui.LessonPlanState;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan implements Cloneable{
    private String lessonTitle;
    private List<Event> eventList = new ArrayList<Event>();

    public Event getOneEvent() {
        return eventList.get(0);
    }


    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
        this.eventList.add(new Event("Untitled"));
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }


    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void removeEvent(Event event) {
        eventList.remove(event);
    }

    public void restoreState(LessonPlanState state) {
        // Clear existing cards and add cards from the state
        clearCards();
        for (Event evt : state.getPastLessonPlan().getCopyOfEvents()) {
            addCards(evt.getCards(), evt.getEventTitle()); // TODO: add them to the correct event inside this clas
        }
    }
    public void clearCards() {
        getOneEvent().clearCards();
    }

    public void addCards(List<Card> cards, String eventTitle) {
        List<Card> allCards = getAllCards();
        for (Card card : cards) {

            if (!allCards.contains(card)) {
                getOneEvent().addCard(card);
            }
        }
    }

    public List<Card> getAllCards() {
        List<Card> allCardsInLesson = new ArrayList<>();
        for (Event event : eventList) {
            allCardsInLesson.addAll(event.getCards());
        }
        return allCardsInLesson;
    }

    @Override
    public String toString() {
        return "LessonPlan{" +
                "lessonTitle='" + lessonTitle + '\'' +
                ", events=" + eventList +
                '}';
    }

    public List<Event> getCopyOfEvents() {
        List<Event> copyOfEvents = new ArrayList<>();

        copyOfEvents.add(clone().getOneEvent());

        return copyOfEvents;
    }

    @Override
    public LessonPlan clone() {
        try {
            LessonPlan clone = (LessonPlan) super.clone();
            clone.eventList = new ArrayList<>();
            for (Event event: this.eventList) {
                clone.eventList.add(event.clone());
            }
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
