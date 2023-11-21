package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.List;

public class CategoryFilter implements CardFilter {


    private final List<String> selectedCategorys;

    public CategoryFilter(List<String> selectedOption) {

        this.selectedCategorys = selectedOption;
    }


    @Override
    public boolean matches(Card potentialMatchCard) {
        if (selectedCategorys.isEmpty()) {
            return true;  // if user selected NOTHING, then we assume they aren't filtering by event, so they want EVERYTHING.
        }
        for (String selectedCategory : selectedCategorys) {
            if (selectedCategory.equalsIgnoreCase(potentialMatchCard.getCategory())) {
                return true;
            }
        }
        return false;

    }



}
