package edu.augustana.data;

import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
//EVent name to flowpane

/**
 * Manages the printing of a JavaFX node.
 */
public class PrintManager {

    @FXML
    private Node printedNode;

    /**
     * Constructs a PrintManager with the specified page node.
     *
     * @param page The node to be printed.
     */
    public PrintManager(Node page){
        this.printedNode = page;
    }

    public void setPrintedNode(VBox printedNode) {
        this.printedNode = printedNode;
    }


    /**
     * prints the page :)
     */
    public void printPage() {
        try {
            // Create a printing job
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null && printerJob.showPrintDialog(printedNode.getScene().getWindow())) {
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER,PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
                printerJob.getJobSettings().setPageLayout(pageLayout);

                double scaleX = pageLayout.getPrintableWidth() / printedNode.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / printedNode.getBoundsInParent().getHeight();
                // changed from max to min to fill page
                double scale = Math.min(scaleX, scaleY);

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
