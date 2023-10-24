package edu.augustana.data;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class OpenCSVReader {
    private static final String CSV_FILE_PATH = "src/main/resources/edu/augustana/DEMO1.csv";

    public static void main(String[] args) throws IOException, CsvValidationException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            // Reading Cards One by One in a String array
            String[] nextCard;
            while ((nextCard = csvReader.readNext()) != null) {
                System.out.println("CODE : " + nextCard[0]);
                System.out.println("Event : " + nextCard[1]);
                System.out.println("Category : " + nextCard[2]);
                System.out.println("Title : " + nextCard[3]);
                System.out.println("Pack Folder : " + nextCard[4]);
                System.out.println("Image : " + nextCard[5]);
                System.out.println("Gender : " + nextCard[6]);
                System.out.println("Model Sex : " + nextCard[7]);
                System.out.println("Level : " + nextCard[8]);
                System.out.println("Equipment : " + nextCard[9]);
                System.out.println("Keywords : " + nextCard[10]);
                System.out.println("==========================");
            }
        }
    }
}
