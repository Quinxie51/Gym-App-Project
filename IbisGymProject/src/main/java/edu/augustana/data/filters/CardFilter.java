package edu.augustana.data.filters;

import edu.augustana.data.Card;

import java.util.List;


/**
 * An interface representing a filter for Card objects.
 */
public interface CardFilter {


    /**
     * Checks if a given card matches the filter criteria.
     *
     * @param potentialMatchCard The card to be checked against the filter.
     * @return true if the card matches the filter criteria, false otherwise.
     */
    public boolean matches(Card potentialMatchCard);


}
