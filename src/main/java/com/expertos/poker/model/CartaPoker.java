package com.expertos.poker.model;

import com.expertos.common.model.Carta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CartaPoker extends Carta {
    public CartaPoker(Integer num, Palo palo) {
        super(num, palo);
    }

    public void setNum(Integer num) {
        super.num = num;
    }

    public void setPalo(Palo palo) {
        super.palo = palo;
    }

    public Integer getValue() {
        switch(num) {
            case 1: return 13;
            default: return num - 1;
        }
    }

    @Override
    public String toString() {
        return super.getNumRight() + " " + super.getPaloRight();
    }

    public static List<CartaPoker> readFromFile(String fileName) {
        List<CartaPoker> res = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get("src/main/resources/baraja-poker.csv"));

            for(String linea : lineas) {
                String[] splits = linea.split(";");

                CartaPoker carta = new CartaPoker(Integer.valueOf(splits[0]), Carta.Palo.valueOf(splits[1]));
                res.add(carta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}
