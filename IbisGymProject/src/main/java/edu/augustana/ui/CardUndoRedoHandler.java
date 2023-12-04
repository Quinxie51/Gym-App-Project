package edu.augustana.ui;

import edu.augustana.data.Card;
import javafx.scene.control.Alert;

import java.util.Stack;

public class CardUndoRedoHandler {

    private final Stack<Card> undoStack;
    private final Stack<Card> redoStack;

    public CardUndoRedoHandler() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void saveState(Card card) {
        undoStack.push(new Card(card));
        redoStack.clear();
    }

    public void undo(Card card) {
        if (undoStack.size() > 1) {
            Card previousState = undoStack.pop();
            redoStack.push(new Card(card));
            card.restoreState(previousState);
        } else {
            // Alert the user that there is no previous state to undo
            showAlert("Cannot Undo", "No previous state to undo.");
        }
    }

    public void redo(Card card) {
        if (!redoStack.isEmpty()) {
            Card redoState = redoStack.pop();
            undoStack.push(new Card(card));
            card.restoreState(redoState);
        } else {
            // Alert the user that there is no redo state available
            showAlert("Cannot Redo", "No redo state available.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
