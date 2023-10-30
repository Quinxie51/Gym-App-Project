package edu.augustana.data;

import java.util.Objects;

public class ModelSexFilter implements CardFilter{
    private final String selectedModelSex;

    public ModelSexFilter(String selectedOption) {
        this.selectedModelSex = selectedOption;
    }
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getModelSex(), selectedModelSex);
    }
}
