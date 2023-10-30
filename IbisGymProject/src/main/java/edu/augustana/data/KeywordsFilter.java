package edu.augustana.data;

import java.util.Objects;

//This needs work

public class KeywordsFilter implements CardFilter{
    private final String[] selectedKeywords;

    public KeywordsFilter(String[] selectedOption) {
        this.selectedKeywords = selectedOption;
    }
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getKeywords(), selectedKeywords);
    }
}
