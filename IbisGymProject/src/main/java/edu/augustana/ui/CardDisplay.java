package edu.augustana.ui;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import edu.augustana.data.Card;
import edu.augustana.data.CardDatabase;


public class CardDisplay {

    private final CardDatabase csvReader = new CardDatabase();


    public void display(Stage primaryStage) {
        TableView<Card> table = new TableView<>();

        TableColumn<Card, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Card, String> eventsColumn = new TableColumn<>("Events");
        eventsColumn.setCellValueFactory(new PropertyValueFactory<>("events"));

        TableColumn<Card, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Card, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Card, String> packFolderColumn = new TableColumn<>("Pack Folder");
        packFolderColumn.setCellValueFactory(new PropertyValueFactory<>("packFolder"));

        TableColumn<Card, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Card, String> modelSexColumn = new TableColumn<>("Model Sex");
        modelSexColumn.setCellValueFactory(new PropertyValueFactory<>("modelSex"));

        TableColumn<Card, String> levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        TableColumn<Card, String> equipmentColumn = new TableColumn<>("Equipment");
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));

        TableColumn<Card, String> keywordsColumn = new TableColumn<>("Keywords");
        keywordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));

        // Add columns to the table
        table.getColumns().addAll(codeColumn, eventsColumn, categoryColumn, titleColumn, packFolderColumn, genderColumn, modelSexColumn, levelColumn, equipmentColumn, keywordsColumn);

        // Add data to the table
        List<Card> allCards = CardDatabase.allCards;
        table.getItems().addAll(allCards);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Display");
        primaryStage.show();
    }

}
