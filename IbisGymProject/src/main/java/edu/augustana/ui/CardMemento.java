package edu.augustana.ui;

import edu.augustana.data.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardMemento {
    private final List<CardState> cardStates;

    public CardMemento(List<CardState> cardStates) {
        this.cardStates = new ArrayList<>(cardStates);
    }

    public List<CardState> getCardStates() {
        return cardStates;
    }

    public static class CardState {
        private final String uniqueID;
        private final String code;
        private final String events;
        private final String category;
        private final String title;
        private final String packFolder;
        private final String imagePath;
        private final String gender;
        private final String modelSex;
        private final String level;
        private final String[] equipment;
        private final String[] keywords;

        public CardState(Card card) {
            this.uniqueID = card.getUniqueID();
            this.code = card.getCode();
            this.events = card.getEvent();
            this.category = card.getCategory();
            this.title = card.getTitle();
            this.packFolder = card.getPackFolder();
            this.imagePath = card.getImagePath();
            this.gender = card.getGender();
            this.modelSex = card.getModelSex();
            this.level = card.getLevel();
            this.equipment = card.getEquipment().toArray(new String[0]);
            this.keywords = card.getKeywords().toArray(new String[0]);
        }

        public void restoreState(Card card) {
            card.setUniqueID(this.uniqueID);
            card.setCode(this.code);
            card.setEvent(this.events);
            card.setCategory(this.category);
            card.setTitle(this.title);
            card.setPackFolder(this.packFolder);
            card.setImagePath(this.imagePath);
            card.setGender(this.gender);
            card.setModelSex(this.modelSex);
            card.setLevel(this.level);
            card.setEquipment(Arrays.copyOf(this.equipment, this.equipment.length));
            card.setKeywords(Arrays.copyOf(this.keywords, this.keywords.length));
        }

    }
}
