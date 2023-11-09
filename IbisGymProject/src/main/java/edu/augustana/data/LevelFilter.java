package edu.augustana.data;

import java.util.List;
import java.util.Objects;

public class LevelFilter implements CardFilter{

    private final List<String> selectedLevels;

    public LevelFilter(List<String> selectedOption) {
        this.selectedLevels = selectedOption;
    }

    @Override
    public boolean matches (Card potentialMatchCard) {
        if (selectedLevels.isEmpty()) {
            return true;  // if user selected NOTHING, then we assume they aren't filtering by event, so they want EVERYTHING.
        }
        for (String selectedLevel : selectedLevels) {
            if (selectedLevel.equalsIgnoreCase(potentialMatchCard.getLevel())) {
                return true;
            }
        }
        return false;
    }
}
