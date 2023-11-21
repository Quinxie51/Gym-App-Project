package edu.augustana.data;

import static org.junit.jupiter.api.Assertions.*;

import edu.augustana.data.CardDatabase;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CardDatabaseTest {
    @Test
    public void testGetCardFromUniqueID() throws IOException {
        Platform.startup(()->{});
        CardDatabase.addCardsFromCSVFile();

        // Pick a unique ID from the loaded cards
        String uniqueID = CardDatabase.getAllCards().get(0).getUniqueID();

        // Check if the returned card matches the expected unique ID
        assertEquals(uniqueID, CardDatabase.getCardFromUniqueID(uniqueID).getUniqueID());
    }
}
