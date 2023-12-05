package edu.augustana.data;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    private Event oneEvent = new Event("Untitled");

    public Event getOneEvent() {
        return oneEvent;
    }

    public void setOneEvent(Event event) {
        this.oneEvent = event;
    }

    private String lessonTitle;
    private LessonPlan lessonPlan;

    private List<String> cardIdList = new ArrayList<>();
    private static List<Event> eventList = new ArrayList<Event>();
    private Event event;

    public LessonPlan(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public LessonPlan(LessonPlan otherLessonPlan) {
        // Copy the values from the otherLessonPlan to initialize the new LessonPlan object
        this.lessonTitle = otherLessonPlan.lessonTitle;
        this.cardIdList = new ArrayList<>(otherLessonPlan.cardIdList);
    }


    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }


    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void removeEvent(Event event) {
        eventList.remove(event);
    }
}



    //public Map<String,List<Card>> getCardListsByEvent(){
        // would return a map like:
        //  { "floor" : [ FloorCard1, FloorCard2 ],
        //   "vault" : [ VaultCards... ],
        //
    //}

