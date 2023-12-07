package edu.augustana.data;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card implements Cloneable{


    //data fields

    private String uniqueID;
    private String code;
    private String events;
    private String category;
    private String title;
    private String packFolder;

    //We could also use an Image object for this
    private Image image;
    private String gender;
    private String imagePath;
    private String modelSex;
    private String level;
    //We possibly need a string[] or a list of strings for this
    private String[] equipment;
    private String[] keywords;


    /**
     * @param selectedUniqueID
     * @param selectedCode
     * @param selectedEvent
     * @param selectedCategory
     * @param selectedTitle
     * @param selectedPackFolder
     * @param selectedImagePath
     * @param selectedGender
     * @param selectedModelSex
     * @param selectedLevel
     * @param selectedEquipment
     * @param selectedKeyWords
     */
    public Card(String selectedUniqueID, String selectedCode, String selectedEvent, String selectedCategory, String selectedTitle, String selectedPackFolder,
                String selectedImagePath, String selectedGender, String selectedModelSex, String selectedLevel,
                String[] selectedEquipment, String[] selectedKeyWords) {


        this.uniqueID = selectedUniqueID;
        this.code = selectedCode;
        this.events = selectedEvent;
        this.category = selectedCategory;
        this.title = selectedTitle;
        this.packFolder = selectedPackFolder;
        this.imagePath = selectedImagePath;
        // load in all thumbnails and use method to get full image size
        this.image = new Image("file:CardPack/" + selectedPackFolder + "/" + selectedImagePath);
        this.gender = selectedGender;
        this.modelSex = selectedModelSex;
        this.level = selectedLevel;
        this.equipment = selectedEquipment;
        this.keywords = selectedKeyWords;

    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getCode() {
        return code;
    }

    public String getEvent() {
        return events;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getPackFolder() {
        return packFolder;
    }

    public Image getImage() {
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
        for (String word : equipment) {
            equipmentList.add(word);

        }
        return equipmentList;
    }

    public List<String> getKeywords() {
        List<String> keywordList = new ArrayList<>();
        for (String word : keywords) {
            keywordList.add(word);

        }
        return keywordList;


    }


    @Override
    public String toString() {
        /**
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
         **/
        // Code: title [gender]
        return code + ": " + title + " [" + gender + "]";
    }

    public String getImagePath() {
        return imagePath;
    }

    public Card(Card other) {
        this.uniqueID = other.uniqueID;
        this.code = other.code;
        this.events = other.events;
        this.category = other.category;
        this.title = other.title;
        this.packFolder = other.packFolder;
        this.imagePath = other.imagePath;
        this.image = new Image("file:CardPack/" + other.packFolder + "/" + other.imagePath);
        this.gender = other.gender;
        this.modelSex = other.modelSex;
        this.level = other.level;
        this.equipment = Arrays.copyOf(other.equipment, other.equipment.length);
        this.keywords = Arrays.copyOf(other.keywords, other.keywords.length);
    }

    public void restoreState(Card savedState) {
        this.uniqueID = savedState.uniqueID;
        this.code = savedState.code;
        this.events = savedState.events;
        this.category = savedState.category;
        this.title = savedState.title;
        this.packFolder = savedState.packFolder;
        this.imagePath = savedState.imagePath;
        this.image = new Image("file:CardPack/" + savedState.packFolder + "/" + savedState.imagePath);
        this.gender = savedState.gender;
        this.modelSex = savedState.modelSex;
        this.level = savedState.level;
        this.equipment = Arrays.copyOf(savedState.equipment, savedState.equipment.length);
        this.keywords = Arrays.copyOf(savedState.keywords, savedState.keywords.length);
    }


    @Override
    public Card clone() {
        try {
            Card clone = (Card) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}