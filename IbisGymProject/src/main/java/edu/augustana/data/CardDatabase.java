package edu.augustana.data;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CardDatabase {
    private static final String CSV_FILE_PATH = "src/main/resources/edu/augustana/DEMO1.csv";
    public static List<Card> allCards = new ArrayList<>();
    private static List<Card> presentableList = new ArrayList<>();
    //private static Map<String,Card> allCardMap;

    public static void main(String[] args) throws IOException, CsvValidationException {
        CardDatabase.addCardsFromCSVFile("DEMO1.csv");
        System.out.println(CardDatabase.getAllCards());
    }

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static List<Card> getFilteredCards(CardFilter filter){
        return allCards;
    }


    public static void addCardsFromCSVFile(String filename) throws IOException{

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            // Reading Cards One by One in a String array
            String[] nextCard;
            while ((nextCard = csvReader.readNext()) != null) {

                String uniqueID = nextCard[5]+"/" +nextCard[4];

                Card currentCard = new Card(uniqueID,nextCard[0],nextCard[1],nextCard[2],nextCard[3],
                        nextCard[4], nextCard[5],nextCard[6],nextCard[7],nextCard[8], nextCard[9], nextCard[10]);
                System.out.println(currentCard.toString());
                allCards.add(currentCard);
            }
            System.out.println();
            System.out.println(allCards);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
/**
        allCards = new CsvToBeanBuilder<Card>(new FileReader(filename)).withType(Card.class).build().parse();
        allCardMap = new HashMap<>();
        for (Card card : allCards) {
            allCardMap.put(card.getUniqueID(), card);
        }
        System.out.println(allCardMap);
**/
    }
}
