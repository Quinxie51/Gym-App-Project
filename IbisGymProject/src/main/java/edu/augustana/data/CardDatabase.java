package edu.augustana.data;
import com.opencsv.CSVReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import edu.augustana.data.filters.CardFilter;

public class CardDatabase {
    private static final String CSV_MAIN_PATH = "CardPack";

    public static List<Card> allCards = new ArrayList<>();

    public static HashMap<String, Card> uniqueIdMap = new HashMap<>();

    private static Set<String> eventSet = new TreeSet<>();

    private static Set<String> categorySet = new TreeSet<>();

    private static Set<String> genderSet = new TreeSet<>();

    private static Set<String> modelSexSet = new TreeSet<>();

    private static Set<String> levelSet = new TreeSet<>();

    private static Set<String> equipmentSet = new TreeSet<>();



    public static void main(String[] args) throws IOException, CsvValidationException {
        //CardDatabase.addCardsFromCSVFile();

        //addCardsFromCSVFile(args);

        List<CardFilter> filters = new ArrayList<>();

        String targetCat = "Shapes";

        System.out.println(eventSet);
        System.out.println(categorySet);
        System.out.println(genderSet);
        System.out.println(modelSexSet);
        System.out.println(levelSet);
        System.out.println(equipmentSet);

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

    public static void addCardsFromCSVFile() throws IOException {
        File mainFolder = new File(CSV_MAIN_PATH);

        if (mainFolder.exists() && mainFolder.isDirectory()) {
            File[] subFolders = mainFolder.listFiles(File::isDirectory);

            if (subFolders != null) {
                for (File subFolder : subFolders) {
                    File[] filesInSubFolder = subFolder.listFiles();

                    if (filesInSubFolder != null) {
                        for (File file : filesInSubFolder) {
                            if (file.isFile() && file.getName().endsWith(".csv")) {
                                try (
                                        Reader reader = Files.newBufferedReader(Path.of(file.toURI()));
                                        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
                                ) {
                                    // Reading Cards One by One in a String array
                                    String[] nextCard;
                                    while ((nextCard = csvReader.readNext()) != null) {

                                        String uniqueID = nextCard[4]+"/" +nextCard[5];
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
                            }
                        }
                    }
                }
            }
        }
    }


    public static Card getCardFromUniqueID(String id) {
        return uniqueIdMap.get(id);
    }

    public static Set<String> getEventSet() {
        return eventSet;
    }

    public static Set<String> getCategorySet() {
        return categorySet;
    }

    public static Set<String> getGenderSet() {
        return genderSet;
    }

    public static Set<String> getModelSexSet() {
        return modelSexSet;
    }

    public static Set<String> getLevelSet() {
        return levelSet;
    }

    public static Set<String> getEquipmentSet() {
        return equipmentSet;
    }



}
