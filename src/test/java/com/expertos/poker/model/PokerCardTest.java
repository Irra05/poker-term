package com.expertos.poker.model;

import com.expertos.common.model.Card;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PokerCardTest {
    private static void printCards(Collection<PokerCard> cards) {
        for(PokerCard card : cards)
            System.out.println("Carta: " + card + ", con valor: " + card.getValue());
    }

    public static void main(String[] args) {

        PokerCard testCard = new PokerCard(10, Card.Suit.HEARTS);
        System.out.println("===== Carta de prueba =====\n");
        System.out.println(testCard + ", número: " + testCard.getNum() + ", palo: " + testCard.getSuit() + ", valor en póker: " + testCard.getValue());

        List<PokerCard> cards = PokerCard.readFromFile("main/resources/baraja-poker.csv");
        System.out.println("\n===== Lista de cartas =====\n");
        printCards(cards);

        SortedSet<PokerCard> cardsSorted = new TreeSet<>(cards);
        System.out.println("\n===== SortedSet de cartas =====\n");
        printCards(cardsSorted);
    }
}
