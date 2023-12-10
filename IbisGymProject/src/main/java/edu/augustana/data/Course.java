package edu.augustana.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a course containing lesson plans.
 */
public class Course {

    //TODO: eventually make a list of lesson plans
    private LessonPlan oneLessonPlan = new LessonPlan("Test");

    public LessonPlan getOneLessonPlan() {
        return oneLessonPlan;
    }
    /**
     * Loads a Course object from the specified file.
     *
     * @param courseFile The file containing the serialized Course object.
     * @return The Course object loaded from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static Course loadFromFile(File courseFile) throws IOException {
        FileReader reader = new FileReader(courseFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,Course.class);
    }
    /**
     * Saves the Course object to the specified file.
     *
     * @param courseFile The file to save the serialized Course object.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void saveToFile(File courseFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedCourseText = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(courseFile));
        writer.println(serializedCourseText);
        writer.close();
    }


}

