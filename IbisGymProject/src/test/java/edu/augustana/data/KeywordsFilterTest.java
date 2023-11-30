package edu.augustana.data;
import edu.augustana.data.filters.KeywordsFilter;
import org.junit.Test;
import static org.junit.Assert.*;

public class KeywordsFilterTest {

    @Test
    public void testMatches() {
        // Create a sample card with keywords
        String[] cardKeywords = {"keyword1", "keyword2", "keyword3"};
        Card testCard = new Card("1", "ABC", "Event", "Category", "Title", "PackFolder", "ImagePath",
                "Gender", "ModelSex", "Level", new String[]{"Equipment1", "Equipment2"}, cardKeywords);

        // Test case 1: Single matching keyword
        KeywordsFilter filter1 = new KeywordsFilter(new String[]{"keyword1"});
        assertTrue(filter1.matches(testCard));

        // Test case 2: Multiple matching keywords
        KeywordsFilter filter2 = new KeywordsFilter(new String[]{"keyword2", "keyword3"});
        assertTrue(filter2.matches(testCard));

        // Test case 3: No matching keywords
        KeywordsFilter filter3 = new KeywordsFilter(new String[]{"nonexistent"});
        assertFalse(filter3.matches(testCard));

        // Test case 4: Empty array (should always return true)
        KeywordsFilter filter4 = new KeywordsFilter(new String[]{});
        assertTrue(filter4.matches(testCard));
    }
}
