package edu.augustana.data;

import static org.junit.jupiter.api.Assertions.*;

import edu.augustana.data.CardDatabase;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CardDatabaseTest {

    @Test
    public void testGetCardFromUniqueID() throws IOException {
        CardDatabase.addCardsFromCSVFile();

        // Pick a unique ID from the loaded cards
        String uniqueID = CardDatabase.getAllCards().get(0).getUniqueID();

        // Check if the returned card matches the expected unique ID
        assertEquals(uniqueID, CardDatabase.getCardFromUniqueID(uniqueID).getUniqueID());
    }

    @Test
    public void testGetCardFromNonexistentUniqueID() throws IOException {
        Platform.startup(()->{});
        CardDatabase.addCardsFromCSVFile();

        // Provide a unique ID that doesn't exist in the loaded cards
        String nonexistentUniqueID = "NonexistentID";

        // The returned card should be null for a nonexistent ID
        assertNull(CardDatabase.getCardFromUniqueID(nonexistentUniqueID));
    }

}
