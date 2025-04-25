package com.expertos.poker.helpers;

import com.expertos.poker.model.PokerCard;

import java.util.Collection;
import java.util.SortedSet;

// TODO: Renombrar esta clase y ponerla en inglés
public record Play(SortedSet<PokerCard> cards, Type type) implements Comparable<Play> {

    public static enum Type {
        CARTA, PAREJA, DOBLE_PAREJA, TRIO, ESCALERA, COLOR, FULL, POKER, ESCALERA_COLOR;
    }

    public Play {
        if(cards.size() != 2 && cards.size() != 5)
            throw new IllegalArgumentException("You can only play with 2 (preflop) or 5 cards");
    }

    public static <E extends Comparable<E>> int compareAllElementsOf(Collection<E> first, Collection<E> second) {
        // TODO

        return 0;
    }

    @Override
    public int compareTo(Play other) {
        Integer r = this.type().compareTo(other.type());

        if(r.equals(0)) {
            r = compareAllElementsOf(this.cards(), other.cards());
        }

        return r;
    }

}
