package edu.augustana.ui;

import edu.augustana.data.LessonPlan;

public class LessonPlanMemento {
    private final LessonPlan lessonPlan;

    public LessonPlanMemento(LessonPlan lessonPlan) {
        this.lessonPlan = new LessonPlan(lessonPlan); // Assuming LessonPlan has a copy constructor
    }

    public LessonPlan getLessonPlan() {
        return lessonPlan;
    }
}
