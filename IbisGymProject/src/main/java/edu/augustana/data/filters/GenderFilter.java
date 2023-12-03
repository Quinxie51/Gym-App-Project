package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.List;

public class GenderFilter implements CardFilter {

    private final List<String> selectedGenders;

    public GenderFilter(List<String> selectedOption) {
        this.selectedGenders = selectedOption;
    }




    @Override
    public boolean matches(Card potentialMatchCard) {
        if (selectedGenders.isEmpty()) {
            return true;  // if user selected NOTHING, then we assume they aren't filtering by event, so they want EVERYTHING.
        }
        for (String selectedGender : selectedGenders) {
            if (selectedGender.equalsIgnoreCase(potentialMatchCard.getGender())||selectedGender.equalsIgnoreCase("N")) {
                return true;
            }
        }
        return false;

    }
}