package com.expertos.common.model;

import java.io.FileWriter;
import java.io.IOException;

public class BarajaPokerGenerator {
    public static void main(String[] args) {
        FileWriter file;
        Carta.Palo p;

        try {
            file = new FileWriter("src/main/resources/baraja-poker.csv");

            for(int i = 0; i < 4; i++) {
                switch(i) {
                    case 0: {
                        p = Carta.Palo.PICAS;
                        break;
                    }
                    case 1: {
                        p = Carta.Palo.TREBOLES;
                        break;
                    }
                    case 2: {
                        p = Carta.Palo.DIAMANTES;
                        break;
                    }
                    case 3: {
                        p = Carta.Palo.CORAZONES;
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

            file.write("0;COMODIN\n");
            file.write("0;COMODIN\n");

            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("♠♣♦");

    }
}
