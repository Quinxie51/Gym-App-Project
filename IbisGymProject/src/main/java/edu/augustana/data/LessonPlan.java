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

    public List<Event> getEventList() {
        return eventList;
    }

    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
        //this.eventList.add(new Event("Untitled"));
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
            addCards(evt.getCards(), evt.getEventTitle()); // TODO: add them to the correct event inside this class
        }
    }
    public void clearCards() {
        for (Event event : getEventList()) {
            event.clearCards();
        }
        //getOneEvent().clearCards();
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
        // change to better string for list view
        return "LessonPlan{" +
                "lessonTitle='" + lessonTitle + '\'' +
                ", events=" + eventList +
                '}';
    }

    public List<Event> getCopyOfEvents() {
        List<Event> copyOfEvents = new ArrayList<>();
        for (Event event : getEventList()) {
            copyOfEvents.add(clone().getOneEvent());
        }
        return clone().getEventList();
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

    public String getDebugText() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Event event: this.eventList) {
            sb.append("[" + event.getEventTitle() + ": ");
            for (Card c : event.getCards()) {
                sb.append(c.getCode() + " ");
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
}
