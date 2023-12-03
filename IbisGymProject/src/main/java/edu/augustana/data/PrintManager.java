package edu.augustana.data;

import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
//EVent name to flowpane
public class PrintManager {

    @FXML
    private Node printedNode;


    public PrintManager(Node page){
        this.printedNode = page;
    }

    public void setPrintedNode(VBox printedNode) {
        this.printedNode = printedNode;
    }


    public void printPage() {
        try {
            // Create a printing job
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null && printerJob.showPrintDialog(printedNode.getScene().getWindow())) {
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER,PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
                printerJob.getJobSettings().setPageLayout(pageLayout);


                // printerJob.getJobSettings().setPageLayout(pageLayout);
//                WritableImage snapshot = printedVbox.snapshot(new SnapshotParameters(),null);
//                double x = pageLayout.getPrintableWidth()/ snapshot.getWidth();
//                double y =pageLayout.getPrintableHeight()/snapshot.getHeight();
//                double scale = Math.min(x,y);

                // printerJob.printPage(snapshot.getPixelReader(),pageLayout,0,0,snapshot.getWidth()*scale,snapshot.getHeight()*scale);
                double scaleX = pageLayout.getPrintableWidth() / printedNode.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / printedNode.getBoundsInParent().getHeight();
                double scale = Math.max(scaleX, scaleY);

                printedNode.getTransforms().add(new Scale(scale, scale));
                if(printerJob.printPage(printedNode)){
                    printerJob.endJob();
                    printedNode.getTransforms().clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
