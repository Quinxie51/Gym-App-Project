package edu.augustana.data;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryFilter implements CardFilter {


    private final String selectedCategory;

    public CategoryFilter(String selectedOption) {

        this.selectedCategory = selectedOption;
    }


    @Override
    public boolean matches(Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getCategory(), selectedCategory);
    }



}
