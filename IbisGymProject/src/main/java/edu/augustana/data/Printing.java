package edu.augustana.data;

import edu.augustana.ui.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

public class Printing {

    @FXML
    private VBox printedVbox;


    public Printing(VBox page){
        this.printedVbox = page;
    }

    public void setPrintedVbox(VBox printedVbox) {
        this.printedVbox = printedVbox;
    }


    public void printPage() {
        try {
            // Create a printing job
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null && printerJob.showPrintDialog(printedVbox.getScene().getWindow())) {
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A4,PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
                printerJob.getJobSettings().setPageLayout(pageLayout);


                //                printerJob.getJobSettings().setPageLayout(pageLayout);
//                WritableImage snapshot = printedVbox.snapshot(new SnapshotParameters(),null);
//                double x = pageLayout.getPrintableWidth()/ snapshot.getWidth();
//                double y =pageLayout.getPrintableHeight()/snapshot.getHeight();
//                double scale = Math.min(x,y);

                // printerJob.printPage(snapshot.getPixelReader(),pageLayout,0,0,snapshot.getWidth()*scale,snapshot.getHeight()*scale);
                double scaleX = pageLayout.getPrintableWidth() / printedVbox.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / printedVbox.getBoundsInParent().getHeight();

                printedVbox.getTransforms().add(new Scale(scaleX, scaleY));
                if(printerJob.printPage(printedVbox)){
                    printerJob.endJob();
                    printedVbox.getTransforms().clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
