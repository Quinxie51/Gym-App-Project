package edu.augustana.ui;

import edu.augustana.data.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {
    private Card myCard;

    public CardImageView(Image image, Card myCard) {
        super(image);
        this.myCard = myCard;
    }

    public Card getMyCard() {
        return myCard;
    }
}
