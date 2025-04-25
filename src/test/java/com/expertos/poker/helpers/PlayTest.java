package com.expertos.poker.helpers;

import com.expertos.poker.model.PokerCard;

import java.util.*;

public class PlayTest {
    public static void main(String[] args) {
        List<PokerCard> cards = PokerCard.readFromFile("main/resources/baraja-poker.csv");

        List<PokerCard> list1 = new ArrayList<>(Set.of(cards.get(23), cards.get(42), cards.getFirst(), cards.get(8), cards.get(34)));
        List<PokerCard> list2 = new ArrayList<>(Set.of(cards.get(37), cards.get(24), cards.get(10), cards.get(51), cards.get(20)));
        Play playTest1 = new Play(list1, Play.Type.PAIR);
        Play playTest2 = new Play(list2, Play.Type.PAIR);
        System.out.println("\n===== Play tests =====");
        System.out.println(playTest1);
        System.out.println(playTest2);

        System.out.println("\n===== Comparaciones =====");
        System.out.println(playTest1.compareTo(playTest2));
    }
}
