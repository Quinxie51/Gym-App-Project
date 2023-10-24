module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana.data;
    opens edu.augustana.data to javafx.fxml;
    exports edu.augustana.ui;
    opens edu.augustana.ui to javafx.fxml;
}
