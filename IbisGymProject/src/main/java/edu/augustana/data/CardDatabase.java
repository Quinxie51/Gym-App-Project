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

    private static List<String> uniqueIDList = new ArrayList<>();
    public static HashMap<String, Card> uniqueIdMap = new HashMap<>();

    public static void main(String args) throws IOException, CsvValidationException {

        CardDatabase.addCardsFromCSVFile("DEMO1.csv");
        System.out.println(CardDatabase.getAllCards());
        //addCardsFromCSVFile(args);

        // Create filter instances based on your criteria
        List<CardFilter> filters = new ArrayList<>();

        // Filter by gender
        String targetCat = "Shapes";

        CardFilter cards = new CategoryFilter(targetCat);

        // Get filtered cards using multiple filters
        List<Card> filteredCards = getFilteredCards(cards);

        // Use the filtered cards as needed
        for (Card card : filteredCards) {
          //  System.out.println(card);
        }
    }

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static List<Card> getFilteredCards(CardFilter filter){
        List<Card> filteredCardList = new ArrayList<>();
        for (Card card : allCards)
            if (filter.matches(card)){
                filteredCardList.add(card);

            }
        return filteredCardList;
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
                uniqueIDList.add(uniqueID);

                Card currentCard = new Card(uniqueID,nextCard[0],nextCard[1],nextCard[2],nextCard[3],
                        nextCard[4], nextCard[5],nextCard[6],nextCard[7],nextCard[8], nextCard[9].split(","), nextCard[10].split(","));
                System.out.println(currentCard.getEquipment());
                allCards.add(currentCard);

                uniqueIdMap.put(uniqueID,currentCard);
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
