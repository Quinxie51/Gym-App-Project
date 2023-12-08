package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.Event;
import edu.augustana.data.LessonPlan;

import java.util.ArrayList;
import java.util.List;

public class LessonPlanState {

    private final LessonPlan pastLessonPlan;

    public LessonPlanState(LessonPlan lessonPlan) {
        this.pastLessonPlan = lessonPlan.clone();
    }

    public LessonPlan getPastLessonPlan() {
        return pastLessonPlan;
    }

    @Override
    public String toString() {
        return pastLessonPlan.getDebugText();
    }
}

