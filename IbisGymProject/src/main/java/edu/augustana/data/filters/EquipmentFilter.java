package edu.augustana.data.filters;

import edu.augustana.data.Card;
import edu.augustana.data.filters.CardFilter;

import java.util.ArrayList;
import java.util.List;
/**
 * A filter implementation for filtering cards based on their equipment.
 */
public class EquipmentFilter implements CardFilter {
    List<String> selectedEquipments = new ArrayList<>();

    /**
     * Constructs an EquipmentFilter with the specified list of selected equipment options.
     *
     * @param selectedOptions The list of selected equipment options for filtering.
     */
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
