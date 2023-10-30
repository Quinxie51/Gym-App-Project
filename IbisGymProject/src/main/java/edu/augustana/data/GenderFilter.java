package edu.augustana.data;

import java.util.Objects;

public class GenderFilter implements CardFilter{

    private final String selectedGender;

    public GenderFilter(String selectedOption) {
        this.selectedGender = selectedOption;
    }
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getGender(), selectedGender);
    }


}
