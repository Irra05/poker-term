package com.expertos.poker.model;

import com.expertos.common.model.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PokerCard extends Card implements Comparable<PokerCard> {
    public PokerCard(Integer num, Suit suit) {
        super(num, suit);
    }

    public void setNum(Integer num) {
        super.num = num;
    }

    public void setColor(Suit suit) {
        super.suit = suit;
    }

    public Integer getValue() {
        switch(num) {
            case 1: return 13;
            default: return num - 1;
        }
    }

    @Override
    public String toString() {
        return "[" + super.getNumRight() + super.getSuitRight() + ']';
    }

    public static List<PokerCard> readFromFile(String fileName) {
        List<PokerCard> res = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/baraja-poker.csv"));

            for(String linea : lines) {
                String[] splits = linea.split(";");

                PokerCard carta = new PokerCard(Integer.valueOf(splits[0]), Suit.valueOf(splits[1]));
                res.add(carta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    // Ordena por valor de la carta de mayor a menor
    // para no tener que hacer SortedSets con comparadores personalizados.
    @Override
    public int compareTo(PokerCard other) {
        Integer toReturn = other.getValue().compareTo(this.getValue());

        if(toReturn.equals(0))
            toReturn = this.getSuit().compareTo(other.getSuit());

        return toReturn;
    }

    public static Comparator<PokerCard> compareByNumber = Comparator.comparing(PokerCard::getNum)
            .thenComparing(PokerCard::getSuit);
}
