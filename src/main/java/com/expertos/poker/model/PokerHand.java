package com.expertos.poker.model;

import com.expertos.common.model.Card;
import com.expertos.poker.helpers.Play;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;

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
    private static Comparator<SortedSet<PokerCard>> createComparatorSortedSetOfEqualNumbers() {

        return new Comparator<SortedSet<PokerCard>>() {
            @Override
            public int compare(SortedSet<PokerCard> o1, SortedSet<PokerCard> o2) {
                Integer toReturn = o2.size() - o1.size();

                if(!toReturn.equals(0))
                    return toReturn;
                else {
                    toReturn = o1.getFirst().getValue().compareTo(o2.getFirst().getValue());
                }

                return toReturn;
            }
        };
    }

    // Se utiliza para encontrar parejas, dobles parejas, tríos, fulls y pokers
    private SortedSet<SortedSet<PokerCard>> findAllSetsEqualNumber() {
        SortedSet<SortedSet<PokerCard>> toReturn =
                new TreeSet<>(createComparatorSortedSetOfEqualNumbers());

        SortedSet<PokerCard> allCards = getAllCards();

        for(PokerCard card : allCards) {
            SortedSet<PokerCard> cards = new TreeSet<>();
            cards.add(card);

            allCards.remove(card);

            for(PokerCard otherCard : allCards)
                if(card.getNum().equals(otherCard.getNum())) {
                    cards.add(otherCard);
                    allCards.remove(otherCard);
                }

            toReturn.add(cards);
        }

        return toReturn;
    }

    // Encuentra colores
    private SortedSet<PokerCard> findColor() {
        SortedSet<PokerCard> toReturn = null;
        // TODO terminar

        Map<Card.Suit, SortedSet<PokerCard>> colors = getAllCards().stream()
                .collect(Collectors.groupingBy(PokerCard::getSuit, Collectors.mapping())); // Como se hace?

        for(Card.Suit color : colors.keySet()) {

        }

        return toReturn;
    }

    // Encuentra escaleras simples
    private SortedSet<PokerCard> findStair() {
        SortedSet<PokerCard> toReturn = new TreeSet<>(
                Comparator.comparing(PokerCard::getNum)
                        .thenComparing(PokerCard::getSuit)
        );
        // TODO

        return toReturn;
    }

    // Encuentra escaleras de color
    private SortedSet<PokerCard> findColorStair() {
        SortedSet<PokerCard> toReturn = new TreeSet<>(
                Comparator.comparing(PokerCard::getNum)
                        .thenComparing(PokerCard::getSuit)
        );
        // TODO

        return toReturn;
    }

    // Este método utiliza los métodos privados "find" anteriores
    // para encontrar la mejor combinación de 5 o 2 cartas.
    public Play getBestPlay() {
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
