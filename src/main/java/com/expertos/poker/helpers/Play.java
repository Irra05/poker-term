package com.expertos.poker.helpers;

import com.expertos.poker.model.PokerCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

// TODO: Renombrar esta clase y ponerla en inglés
public record Play(SortedSet<PokerCard> cards, Type type) implements Comparable<Play> {

    public static enum Type {
        SINGLE, PAIR, DOUBLE_PAIR, TRIO, STAIR, COLOR, FULL_HOUSE, POKER, COLOR_STAIR;
    }

    public Play {
        if(cards.size() != 2 && cards.size() != 5)
            throw new IllegalArgumentException("You can only play with 2 (preflop) or 5 cards");
    }

    public static <E extends Comparable<E>> int compareAllElementsOf(Collection<E> first, Collection<E> second) {
        Integer toReturn = first.size() - second.size();

        if(toReturn.equals(0)) {
            List<E> list1 = new ArrayList<>(first);
            List<E> list2 = new ArrayList<>(second);

            for(int i = 0; i < list1.size(); i++) {
                toReturn = list1.get(i).compareTo(list2.get(i));

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
            toReturn = compareAllElementsOf(this.cards(), other.cards());
        }

        return toReturn;
    }

}
