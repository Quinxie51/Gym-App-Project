package edu.augustana.data.filters;

import edu.augustana.data.Card;

import java.util.List;

public interface CardFilter {

    public boolean matches(Card potentialMatchCard);


}
