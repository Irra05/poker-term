package com.expertos.poker.helpers;

import com.expertos.poker.model.PokerCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Play(List<PokerCard> cards, Type type) implements Comparable<Play> {

    public static enum Type {
        SINGLE, PAIR, DOUBLE_PAIR, TRIO, STAIR, COLOR, FULL_HOUSE, POKER, COLOR_STAIR;
    }

    public Play {
        if(cards.size() != 2 && cards.size() != 5)
            throw new IllegalArgumentException("You can only play with 2 (preflop) or 5 cards");
    }

    private static int compareAllElementsOfPlays(Play first, Play second) {
        if(first.cards().size() != second.cards().size())
            throw new IllegalArgumentException("Compared plays must have the same amount of cards");

        Integer toReturn = null;

        List<PokerCard> copy1 = new ArrayList<>(first.cards()), copy2 = new ArrayList<>(second.cards());

        if(first.type().equals(second.type()) &&
                (first.type().equals(Type.COLOR) || first.type().equals(Type.STAIR) || first.type().equals(Type.COLOR_STAIR))){

            Collections.sort(copy1);
            Collections.sort(copy2);
        }

        for(int i = 0; i < copy1.size(); i++) {
            toReturn = copy2.get(i).compareTo(copy1.get(i));

            if(!toReturn.equals(0))
                break;
        }

        return toReturn;
    }

    @Override
    public int compareTo(Play other) {
        Integer toReturn = this.type().compareTo(other.type());

        if(toReturn.equals(0)) {
            toReturn = compareAllElementsOfPlays(this, other);
        }

        return toReturn;
    }

}
