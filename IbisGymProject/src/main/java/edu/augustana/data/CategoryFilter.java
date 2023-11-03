package edu.augustana.data;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryFilter implements CardFilter{

    private final String selectedCategory;
    HashMap<String, ArrayList<String>> categoryMap = new HashMap<>();

    public CategoryFilter(String selectedOption) {
        this.selectedCategory = selectedOption;
    }

    void setCategoryMap(String key, String value) {
        if (categoryMap.containsKey(key)) {
            categoryMap.get(key).add(value);
        } else {
            ArrayList<String> values = new ArrayList<>();
            values.add(value);
            categoryMap.put(key, values);
        }
    }


    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getCategory(), selectedCategory);
    }



}
