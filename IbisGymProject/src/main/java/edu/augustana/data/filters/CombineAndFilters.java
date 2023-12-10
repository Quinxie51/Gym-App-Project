package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;
/**
 *Code cited from Dr. Forrest Stonedahl from MovieTrackerApp
 */
public class CombineAndFilters implements CardFilter {
    
    CardFilter[] filters;

    /**
     * Constructs a CombineAndFilters with the specified array of filters.
     *
     * @param filters The filters to be combined using the logical AND operation.
     */
    public CombineAndFilters(CardFilter... filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(Card potentialMatchCard) {
        for (CardFilter filter : filters) {
            if (!filter.matches(potentialMatchCard)) {
                return false;
            }
        }
        return true;
    }
}
