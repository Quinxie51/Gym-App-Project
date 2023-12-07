package edu.augustana.ui;

import edu.augustana.data.LessonPlan;
import javafx.scene.control.Alert;

import java.util.Stack;

public class CardUndoRedoHandler {

    private final Stack<LessonPlanState> undoStack;
    private final Stack<LessonPlanState> redoStack;

    public CardUndoRedoHandler(LessonPlan initialState) {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        saveState(initialState);
    }

    public void saveState(LessonPlan lessonPlan) {
        System.out.println("Saving state: " + lessonPlan.toString());
        undoStack.push(new LessonPlanState(lessonPlan));
        System.out.println("After save state, undostack=");
        System.out.println(undoStack);
        System.out.println("-----------------");
    }

    public void undo(LessonPlan lessonPlan) {
        System.out.println("Before undo, undostack=");
        System.out.println(undoStack);
        System.out.println("-----------------");

        if (undoStack.size() > 1) {
            LessonPlanState currentState = undoStack.pop();
            redoStack.push(currentState);
            LessonPlanState previousState = undoStack.peek();
            lessonPlan.restoreState(previousState);
        } else {
            showAlert("Cannot Undo", "No previous state to undo.");
        }
    }

    public void redo(LessonPlan lessonPlan) {
        System.out.println("Before redo, undostack=");
        System.out.println(undoStack);
        System.out.println("-----------------");

        if (!redoStack.isEmpty()) {
            LessonPlanState redoState = redoStack.pop();
            undoStack.push(redoState);
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
