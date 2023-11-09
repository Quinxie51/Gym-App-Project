package edu.augustana.data;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

    private static Set<String> eventSet = new TreeSet<>();

    private static Set<String> categorySet = new TreeSet<>();

    private static HashSet<String> genderSet = new HashSet<>();

    private static HashSet<String> modelSexSet = new HashSet<>();

    private static HashSet<String> levelSet = new HashSet<>();

    private static HashSet<String> equipmentSet = new HashSet<>();



    public static void main(String[] args) throws IOException, CsvValidationException {

        CardDatabase.addCardsFromCSVFile("DEMO1.csv");

        //addCardsFromCSVFile(args);

        List<CardFilter> filters = new ArrayList<>();


        String targetCat = "Shapes";

        CardFilter cards = new CategoryFilter(targetCat);

        List<Card> filteredCards = getFilteredCards(cards);
        System.out.println(eventSet);
        System.out.println(categorySet);
        System.out.println(genderSet);
        System.out.println(modelSexSet);
        System.out.println(levelSet);
        System.out.println(equipmentSet);
        for (Card card : filteredCards) {

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

                allCards.add(currentCard);

                uniqueIdMap.put(uniqueID,currentCard);

                eventSet.add(currentCard.getEvent().toUpperCase());
                categorySet.add(currentCard.getCategory().toUpperCase());
                genderSet.add(currentCard.getGender().toUpperCase());
                modelSexSet.add(currentCard.getModelSex().toUpperCase());
                levelSet.add(currentCard.getLevel().toUpperCase());

                for (String equipment : currentCard.getEquipment()){
                    equipmentSet.add(equipment.toUpperCase());
                }


            }
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

    public static Set<String> getEventSet() {
        return eventSet;
    }

    public static Set<String> getCategorySet() {
        return categorySet;
    }

    public static HashSet<String> getGenderSet() {
        return genderSet;
    }

    public static HashSet<String> getModelSexSet() {
        return modelSexSet;
    }

    public static HashSet<String> getLevelSet() {
        return levelSet;
    }

    public static HashSet<String> getEquipmentSet() {
        return equipmentSet;
    }


    public static Card getCardFromUniqueID(String id) {
        return uniqueIdMap.get(id);
    }
}
