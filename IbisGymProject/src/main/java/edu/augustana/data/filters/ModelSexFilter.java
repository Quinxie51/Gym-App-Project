package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.List;
/**
 * A filter implementation for filtering cards based on their model sex.
 */
public class ModelSexFilter implements CardFilter {
    private final List<String> selectedModelSexes;
    /**
     * Constructs a ModelSexFilter with the specified list of selected model sexes.
     *
     * @param selectedOption The list of selected model sexes for filtering.
     */
    public ModelSexFilter(List<String> selectedOption) {
        this.selectedModelSexes = selectedOption;
    }


    @Override
    public boolean matches (Card potentialMatchCard) {
        if (selectedModelSexes.isEmpty()) {
            return true;  // if user selected NOTHING, then we assume they aren't filtering by event, so they want EVERYTHING.
        }
        for (String selectedModelSex : selectedModelSexes) {
            if (selectedModelSex.equalsIgnoreCase(potentialMatchCard.getModelSex())) {
                return true;
            }
        }
        return false;
    }
}
