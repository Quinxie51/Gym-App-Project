package edu.augustana.ui;

import edu.augustana.data.LessonPlan;
import javafx.scene.control.Alert;

import java.util.Stack;

public class CardUndoRedoHandler {

    private final Stack<LessonPlanState> undoStack;
    private final Stack<LessonPlanState> redoStack;

    public CardUndoRedoHandler() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void saveState(LessonPlan lessonPlan) {
        undoStack.push(new LessonPlanState(lessonPlan));
        redoStack.clear();
    }

    public void undo(LessonPlan lessonPlan) {
        if (!undoStack.isEmpty()) {
            LessonPlanState previousState = undoStack.pop();
            redoStack.push(new LessonPlanState(lessonPlan));
            lessonPlan.restoreState(previousState);
        } else {
            showAlert("Cannot Undo", "No previous state to undo.");
        }
    }

    public void redo(LessonPlan lessonPlan) {
        if (!redoStack.isEmpty()) {
            LessonPlanState redoState = redoStack.pop();
            undoStack.push(new LessonPlanState(lessonPlan));
            lessonPlan.restoreState(redoState);
        } else {
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
