package com.expertos.poker.model;

public record Jugada(Tipo tipo, Integer valor) implements Comparable<Jugada> {

    public static enum Tipo {
        CARTA, PAREJA, DOBLE_PAREJA, TRIO, ESCALERA, FULL, POKER, ESCALERA_COLOR, REPOKER;
    }

    @Override
    public int compareTo(Jugada other) {
        Integer r = this.tipo().compareTo(other.tipo());

        if(r.equals(0))
            r = this.valor().compareTo(other.valor());

        return r;
    }

}
