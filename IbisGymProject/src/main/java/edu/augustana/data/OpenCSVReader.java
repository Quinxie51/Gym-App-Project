package edu.augustana.data;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class OpenCSVReader {
    private static final String CSV_FILE_PATH = "src/main/resources/edu/augustana/DEMO1.csv";
    public static List<Card> cards = new ArrayList<>();

    public static void main(String[] args) throws IOException, CsvValidationException {
        addCardsFromCSVFile(args);

    }


    public static List<Card> getFilteredCards(CardFilter filter){

        return cards;
    }


// H10:W



    public static void addCardsFromCSVFile(String[] args) throws IOException, CsvValidationException{
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            // Reading Cards One by One in a String array
            String[] nextCard;
            while ((nextCard = csvReader.readNext()) != null) {

                Card currentCard = new Card(nextCard[0],nextCard[1],nextCard[2],nextCard[3],nextCard[4], nextCard[5],nextCard[6],nextCard[7],nextCard[8],nextCard[9],nextCard[10]);
                System.out.println(currentCard.toString());
                cards.add(currentCard);

            }
        }

    }








}
