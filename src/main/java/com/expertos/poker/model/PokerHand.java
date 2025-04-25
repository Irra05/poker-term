package com.expertos.poker.model;

import com.expertos.poker.helpers.Play;

import java.util.*;

public class PokerHand {

    private final SortedSet<PokerCard> table;
    private final SortedSet<PokerCard> hand;

    public PokerHand(SortedSet<PokerCard> table, SortedSet<PokerCard> hand) {
        this.table = table;
        this.hand = hand;
    }

    public SortedSet<PokerCard> getTable() {
        return table;
    }

    public SortedSet<PokerCard> getHand() {
        return hand;
    }

    public SortedSet<PokerCard> getAllCards() {
        SortedSet<PokerCard> toReturn  = new TreeSet<>(getHand());
        toReturn.addAll(getTable());

        return toReturn;
    }

    // El comparator se me ha complicado
    private static Comparator<SortedSet<PokerCard>> createComparatorSetOfEqualNumbers() {
        Comparator<SortedSet<PokerCard>> cmp = new Comparator<SortedSet<PokerCard>>() {
            @Override
            public int compare(SortedSet<PokerCard> o1, SortedSet<PokerCard> o2) {
                Integer toReturn = o2.size() - o1.size();

                if(!toReturn.equals(0))
                    return toReturn;
                else {
                    toReturn = o2.getFirst().getValue().compareTo(o1.getFirst().getValue());
                }

                return toReturn;
            }
        };

        return cmp;
    }

    // Se utiliza para encontrar parejas, dobles parejas, tríos, fulls y pokers
    private SortedSet<SortedSet<PokerCard>> findAllSetsEqualNumber() {
        SortedSet<SortedSet<PokerCard>> toReturn =
                new TreeSet<>(createComparatorSetOfEqualNumbers());

        SortedSet<PokerCard> allCards = getAllCards();

        for(PokerCard card : allCards) {
            SortedSet<PokerCard> cards = new TreeSet<>();
            cards.add(card);

            allCards.remove(card);

            for(PokerCard otherCard : allCards) {
                if(card.getNum().equals(otherCard.getNum())) {
                    cards.add(otherCard);
                    allCards.remove(otherCard);
                }
            }
        }

        return toReturn;
    }

    // Encuentra colores
    private SortedSet<PokerCard> findColor() {
        SortedSet<PokerCard> toReturn = new TreeSet<>();
        // TODO

        return toReturn;
    }

    // Encuentra escaleras simples
    private SortedSet<PokerCard> findStair() {
        SortedSet<PokerCard> toReturn = new TreeSet<>();
        // TODO

        return toReturn;
    }

    // Encuentra escaleras de color
    private SortedSet<PokerCard> findColorStair() {
        SortedSet<PokerCard> toReturn = new TreeSet<>();
        // TODO

        return toReturn;
    }

    // Este método utiliza los métodos privados "find" anteriores
    // para encontrar la mejor combinación de 5 o 2 cartas.
    public Play getBestCombination() {
        Play toReturn = null;
        //TODO

        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand manoPoker = (PokerHand) o;
        return Objects.equals(table, manoPoker.table) && Objects.equals(hand, manoPoker.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, hand);
    }

}
