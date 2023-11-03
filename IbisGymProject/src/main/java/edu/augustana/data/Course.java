package edu.augustana.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Course {
    //TODO: eventually make a list of lesson plans
    public static LessonPlan currentLessonPlan = new LessonPlan("Untitled");

    public static Course loadFromFile(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,Course.class);
    }

    public void saveToFile(File logFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedMovieLogText = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(logFile));
        writer.println(serializedMovieLogText);
        writer.close();
    }


}
