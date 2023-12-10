package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.Objects;

//This needs work
/**
 * A filter implementation for filtering cards based on their keywords.
 * Note: This implementation needs further work.
 */
public class KeywordsFilter implements CardFilter {
    private final String[] selectedKeywords;

    /**
     * Constructs a KeywordsFilter with the specified array of selected keywords.
     *
     * @param selectedOption The array of selected keywords for filtering.
     */
    public KeywordsFilter(String[] selectedOption) {
        this.selectedKeywords = selectedOption;
    }


    @Override
    public boolean matches (Card potentialMatchCard) {
        for (String word: selectedKeywords){

        }
        return Objects.equals(potentialMatchCard.getKeywords(), selectedKeywords);
    }
}
