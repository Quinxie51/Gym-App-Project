package edu.augustana.data;

import edu.augustana.ui.CardMemento;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
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
    private Image image;
    private String gender;
    private String imagePath;
    private String modelSex;
    private String level;
    //We possibly need a string[] or a list of strings for this
    private String[] equipment;
    private String[] keywords;


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

    private CardMemento.CardState createMemento() {
        return new CardMemento.CardState(this);
    }

    public void restoreFromMemento(CardMemento.CardState cardState) {
        cardState.restoreState(this);
    }
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEvent(String events) {
        this.events = events;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPackFolder(String packFolder) {
        this.packFolder = packFolder;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setModelSex(String modelSex) {
        this.modelSex = modelSex;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setEquipment(String[] equipment) {
        this.equipment = Arrays.copyOf(equipment, equipment.length);
    }

    public void setKeywords(String[] keywords) {
        this.keywords = Arrays.copyOf(keywords, keywords.length);
    }
}