package com.expertos.common.helpers;

import com.expertos.common.model.Card;

import java.io.FileWriter;
import java.io.IOException;

public class BarajaPokerGenerator {
    public static void main(String[] args) {
        FileWriter file;
        Card.Suit p;

        try {
            file = new FileWriter("src/main/resources/baraja-poker.csv");

            for(int i = 0; i < 4; i++) {
                switch(i) {
                    case 0: {
                        p = Card.Suit.PICAS;
                        break;
                    }
                    case 1: {
                        p = Card.Suit.TREBOLES;
                        break;
                    }
                    case 2: {
                        p = Card.Suit.DIAMANTES;
                        break;
                    }
                    case 3: {
                        p = Card.Suit.CORAZONES;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("Unexpected value: " + i);
                    }
                }

                for(Integer num = 1; num <= 13; num++) {
                    file.write(num.toString() + ';' + p.toString() + '\n');
                }
            }

            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("♠♣♦");

    }
}
