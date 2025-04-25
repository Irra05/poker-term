package com.expertos.poker.helpers;

import com.expertos.poker.model.PokerCard;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public record Play(List<PokerCard> cards, Type type) implements Comparable<Play> {

    public static enum Type {
        SINGLE, PAIR, DOUBLE_PAIR, TRIO, STAIR, COLOR, FULL_HOUSE, POKER, COLOR_STAIR;
    }

    public Play {
        if(cards.size() != 2 && cards.size() != 5)
            throw new IllegalArgumentException("You can only play with 2 (preflop) or 5 cards");
    }

    private static int compareAllElementsOfPlays(List<PokerCard> first, List<PokerCard> second) {
        Integer toReturn = first.size() - second.size();

        if(toReturn.equals(0)) {
            for(int i = 0; i < first.size(); i++) {
                toReturn = second.get(i).compareTo(first.get(i));

                if(!toReturn.equals(0))
                    break;
            }
        }

        return toReturn;
    }

    @Override
    public int compareTo(Play other) {
        Integer toReturn = this.type().compareTo(other.type());

        if(toReturn.equals(0)) {
            toReturn = compareAllElementsOfPlays(this.cards(), other.cards());
        }

        return toReturn;
    }

}
