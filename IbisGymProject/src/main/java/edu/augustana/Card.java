package edu.augustana;

import javafx.fxml.FXML;

public class Card {

    //data fields
    private String code;
    private String event;
    private String title;
    private String packFolder;
    private String image;
    private String gender;
    private String modelSex;
    private String level;
    private String equipment;
    private String [] keywords;

    public Card(String selectedCode, String selectedEvent, String selectedTitle,String selectedPackFolder,
                String selectedImage, String selectedGender,String selectedModelSex, String selectedLevel,
                String selectedEquipment,String[] selectedKeyWords){

        this.code = selectedCode;
        this.event = selectedEvent;
        this.title = selectedTitle;
        this.packFolder = selectedPackFolder;
        this.image = selectedImage;
        this.gender = selectedGender;
        this.modelSex = selectedModelSex;
        this.level = selectedLevel;
        this.equipment = selectedEquipment;
        this.keywords = selectedKeyWords;


    }

}
