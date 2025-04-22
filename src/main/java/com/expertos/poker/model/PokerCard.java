package com.expertos.poker.model;

import com.expertos.common.model.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PokerCard extends Card implements Comparable<PokerCard> {
    public PokerCard(Integer num, Color color) {
        super(num, color);
    }

    public void setNum(Integer num) {
        super.num = num;
    }

    public void setColor(Color color) {
        super.color = color;
    }

    public Integer getValue() {
        switch(num) {
            case 1: return 13;
            default: return num - 1;
        }
    }

    @Override
    public String toString() {
        return super.getNumRight() + " " + super.getColorRight();
    }

    public static List<PokerCard> readFromFile(String fileName) {
        List<PokerCard> res = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/baraja-poker.csv"));

            for(String linea : lines) {
                String[] splits = linea.split(";");

                PokerCard carta = new PokerCard(Integer.valueOf(splits[0]), Color.valueOf(splits[1]));
                res.add(carta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public int compareTo(PokerCard other) {
        Integer toReturn = this.getValue().compareTo(other.getValue());

        if(toReturn.equals(0))
            toReturn = this.getColor().compareTo(other.getColor());

        return toReturn;
    }
}
