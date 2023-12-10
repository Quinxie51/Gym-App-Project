package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.List;
/**
 * A filter implementation for filtering cards based on their level.
 */
public class LevelFilter implements CardFilter {

    private final List<String> selectedLevels;
    /**
     * Constructs a LevelFilter with the specified list of selected levels.
     *
     * @param selectedOption The list of selected levels for filtering.
     */
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
