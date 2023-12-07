package edu.augustana.ui;

import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class LimitedFlowPane extends FlowPane {
//Source, chatGPT
    private final int maxNodes;

    public LimitedFlowPane(int maxNodes) {
        this.maxNodes = maxNodes;
    }

    public void addWithinMax(Node... children) {
        if (getChildren().size() + children.length <= maxNodes) {
            super.getChildren().addAll(children);
        } else {
            throw new IllegalArgumentException("Cannot add more nodes. Maximum allowed: " + maxNodes);
        }
    }
}