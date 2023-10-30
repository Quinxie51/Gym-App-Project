package edu.augustana.data;

import java.util.Objects;

public class EventFilter implements CardFilter{

    private final String selectedEvent;

    public EventFilter(String selectedOption) {
        this.selectedEvent = selectedOption;
    }
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getEvent(), selectedEvent);
    }
}
