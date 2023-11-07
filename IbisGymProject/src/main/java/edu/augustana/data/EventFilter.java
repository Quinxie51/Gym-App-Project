package edu.augustana.data;

import java.util.List;
import java.util.Objects;

public class EventFilter implements CardFilter{

    private final String selectedEvent;

    public EventFilter(String selectedOption) {
        this.selectedEvent = selectedOption;
    }


    @Override
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getEvent(), selectedEvent);
    }
}
