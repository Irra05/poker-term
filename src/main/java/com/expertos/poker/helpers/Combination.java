package com.expertos.poker.helpers;

import com.expertos.poker.model.PokerCard;

import java.util.SortedSet;

public class Combination {
    private SortedSet<PokerCard> cards;
    private Jugada bestPlay;

    public Combination(SortedSet<PokerCard> cartas) {
        if(cartas.size() != 2 && cartas.size() != 5)
            throw new IllegalArgumentException("cards must have size 2 or 5");

        this.cards = cartas;
    }

    public SortedSet<PokerCard> getCards() {
        return cards;
    }

    public Jugada getBestPlay() {
        return bestPlay;
    }
}
