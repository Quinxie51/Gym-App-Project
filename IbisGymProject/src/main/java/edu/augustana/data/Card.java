package edu.augustana.data;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Card {

    //data fields

    private String uniqueID;
    private String code;
    private String events;

    private String category;
    private String title;
    private String packFolder;

    //We could also use an Image object for this
    private String image;
    private String gender;
    private String modelSex;
    private String level;
    //We possibly need a string[] or a list of strings for this
    private String [] equipment;
    private String[] keywords;



    public Card(String selectedUniqueID, String selectedCode, String selectedEvent, String selectedCategory, String selectedTitle, String selectedPackFolder,
                String selectedImage, String selectedGender, String selectedModelSex, String selectedLevel,
                String [] selectedEquipment, String [] selectedKeyWords) {

        this.uniqueID = selectedUniqueID;
        this.code = selectedCode;
        this.events = selectedEvent;
        this.category = selectedCategory;
        this.title = selectedTitle;
        this.packFolder = selectedPackFolder;
        this.image = selectedImage ;
        this.gender = selectedGender;
        this.modelSex = selectedModelSex;
        this.level = selectedLevel;
        this.equipment = selectedEquipment;
        this.keywords = selectedKeyWords;

    }

    public String getUniqueID() {return uniqueID;}
    public String getCode() {
        return code;
    }
    public String getEvent() {
        return events;
    }
    
    public String getCategory() {return category; }

    public String getTitle() {
        return title;
    }

    public String getPackFolder() {
        return packFolder;
    }

    public String getImage() {
        return image;
    }

    public String getGender() {
        return gender;
    }

    public String getModelSex() {
        return modelSex;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getEquipment() {
        List<String> equipmentList = new ArrayList<>();
        for(String word: equipment){
            equipmentList.add(word);

        }
        return equipmentList;
    }

    public List<String> getKeywords() {
        List<String> keywordList = new ArrayList<>();
        for(String word: keywords){
            keywordList.add(word);

        }
        return keywordList;


    }

    @Override
    public String toString() {
        return "Card{" +
                "uniqueID='" + uniqueID + '\'' +
                ", code='" + code + '\'' +
                ", events='" + events + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", packFolder='" + packFolder + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", modelSex='" + modelSex + '\'' +
                ", level='" + level + '\'' +
                ", equipment='" + equipment + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
