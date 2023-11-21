package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

public class CombineAndFilters implements CardFilter {

    @Override
    public boolean matches(Card potentialMatchCard) {
        return false;
    }
}
