package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.ArrayList;
import java.util.List;

public class EquipmentFilter implements CardFilter {
    List<String> selectedEquipments = new ArrayList<>();

    public EquipmentFilter(List<String> selectedOptions) {
        this.selectedEquipments = selectedOptions;
    }


    @Override
    public boolean matches(Card potentialMatchCard) {
        if (selectedEquipments.isEmpty()) {
            return true;  // if user selected NOTHING, then we assume they aren't filtering by event, so they want EVERYTHING.
        }

        for (String selectedEquipment : selectedEquipments) {
            for (String equipment: potentialMatchCard.getEquipment() )
                if (selectedEquipment.equalsIgnoreCase(equipment)) {
                return true;
            }
        }
        return false;
    }
}
