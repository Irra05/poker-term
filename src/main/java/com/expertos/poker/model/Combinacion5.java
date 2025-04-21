package com.expertos.poker.model;

import java.util.List;
import java.util.Set;

public class Combinacion5 {
    private List<CartaPoker> cartas;

    public Combinacion5(List<CartaPoker> cartas) {
        if(cartas.size() != 2 || cartas.size() != 5)
            throw new IllegalArgumentException("cartas must have size 2 or 5");

        this.cartas = cartas;
    }

    public List<CartaPoker> getCartas() {
        return cartas;
    }

    public Jugada getJugada() {
        Jugada r;

        if(getCartas().size() == 2) {
            r = getJugada2();
        }
        else {
            r = getJugada5();
        }

        return r;
    }

    private Jugada getJugada2() {
        // TODO
        Jugada r = null;

        if(true) {
        }

        return r;
    }

    private Jugada getJugada5() {
        // TODO
        Jugada r = null;

        return r;
    }
}
