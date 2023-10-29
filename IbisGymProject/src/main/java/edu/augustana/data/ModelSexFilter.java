package edu.augustana.data;

import java.util.Objects;

public class ModelSexFilter implements CardFilter{
    private final String selectedCategory;

    public ModelSexFilter(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getModelSex(), selectedCategory);
    }
}
