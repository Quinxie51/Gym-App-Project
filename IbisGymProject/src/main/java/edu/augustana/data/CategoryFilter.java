package edu.augustana.data;

import java.util.Objects;

public class CategoryFilter implements CardFilter{

    private final String selectedCategory;

    public CategoryFilter(String selectedOption) {
        this.selectedCategory = selectedOption;
    }
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getCategory(), selectedCategory);
    }



}
