package csc305.testing;

import static org.junit.jupiter.api.Assertions.*;

import edu.augustana.data.Card;
import edu.augustana.data.CardDatabase;
import edu.augustana.data.CardFilter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static edu.augustana.data.CardDatabase.*;

public class CardDatabaseTest {
    @Test
    public void testGetCardFromUniqueID() throws IOException {
        CardDatabase.addCardsFromCSVFile("DEMO1.csv");

        // Pick a unique ID from the loaded cards
        String uniqueID = CardDatabase.getAllCards().get(0).getUniqueID();

        // Check if the returned card matches the expected unique ID
        assertEquals(uniqueID, CardDatabase.getCardFromUniqueID(uniqueID).getUniqueID());
    }
}
