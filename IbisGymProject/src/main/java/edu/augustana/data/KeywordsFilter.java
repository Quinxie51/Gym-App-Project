package edu.augustana.data;

import java.util.List;
import java.util.Objects;

//This needs work

public class KeywordsFilter implements CardFilter{
    private final String[] selectedKeywords;


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
