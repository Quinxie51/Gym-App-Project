module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires com.google.gson;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana.data;
    opens edu.augustana.data to javafx.fxml, com.google.gson;
    exports edu.augustana.ui;
    opens edu.augustana.ui to javafx.fxml;
    exports edu.augustana.data.filters;
    opens edu.augustana.data.filters to com.google.gson, javafx.fxml;
}
