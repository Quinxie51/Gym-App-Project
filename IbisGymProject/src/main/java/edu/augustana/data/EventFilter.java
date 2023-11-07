package edu.augustana.data;

import java.util.List;
import java.util.Objects;

public class EventFilter implements CardFilter{

    private final List<String> selectedEvents;

    public EventFilter(List<String> selectedOptions) {
        this.selectedEvents = selectedOptions;
    }


    @Override
    public boolean matches (Card potentialMatchCard) {
        if (selectedEvents.isEmpty()) {
            return true;  // if user selected NOTHING, then we assume they aren't filtering by event, so they want EVERYTHING.
        }
        for (String selectedEvent : selectedEvents) {
            if (selectedEvent.equals(potentialMatchCard.getEvent())) {
                return true;
            }
        }
        return false;
    }
}
