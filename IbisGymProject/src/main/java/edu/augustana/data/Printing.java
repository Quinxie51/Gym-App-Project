package edu.augustana.data;

import edu.augustana.ui.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Printing {
    private void Print() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newLessonCreationPage.fxml"));
            Node fxmlContent = loader.load();

            // Create a printing job
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null) {
                // Set the printer and page layout
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
                printerJob.getJobSettings().setPageLayout(pageLayout);

                // Fit the content to the page size
                Scene scene = MainApp.getScene();
                //scene.getRoot().resize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
                //scene.getWindow().setWidth(pageLayout.getPrintableWidth());
                //scene.getWindow().setHeight(pageLayout.getPrintableHeight());

                // Create a separate container for the content to print
                Pane printContainer = new Pane();
                printContainer.getChildren().add(fxmlContent);

                double x = 500; // X coordinate of the top-left corner of the area to print
                double y = -100; // Y coordinate of the top-left corner of the area to print
                double width = 900; // Width of the area to print
                double height = 750; // Height of the area to print

                // Adjust the layout of the printContainer
                printContainer.setLayoutX(-x);
                printContainer.setLayoutY(-y);
                printContainer.setPrefWidth(width);
                printContainer.setPrefHeight(height);

                boolean printed = printerJob.printPage(printContainer);
                if (printed) {
                    printerJob.endJob();
                }

                scene.getRoot().resize(scene.getWidth(), scene.getHeight());
                scene.getWindow().setWidth(scene.getWidth());
                scene.getWindow().setHeight(scene.getHeight());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
