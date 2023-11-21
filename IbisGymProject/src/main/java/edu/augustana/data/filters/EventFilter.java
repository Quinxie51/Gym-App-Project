package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.List;

public class EventFilter implements CardFilter {

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
            if (selectedEvent.equalsIgnoreCase(potentialMatchCard.getEvent())) {
                return true;
            }
        }
        return false;
    }
}
