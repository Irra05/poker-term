package com.expertos.poker.helpers;

// TODO: Renombrar esta clase y ponerla en inglés
public record Jugada(Tipo tipo, Integer valor1,
                     Integer valor2, Integer valor3,
                     Integer valor4, Integer valor5) implements Comparable<Jugada> {

    public static enum Tipo {
        CARTA, PAREJA, DOBLE_PAREJA, TRIO, ESCALERA, COLOR, FULL, POKER, ESCALERA_COLOR;
    }

    @Override
    public int compareTo(Jugada other) {
        Integer r = this.tipo().compareTo(other.tipo());

        if(r.equals(0)) {
            r = this.valor1().compareTo(other.valor1());
        }
        else {
            return r;
        }
        if(r.equals(0)) {
            r = this.valor2().compareTo(other.valor2());
        }
        else {
            return r;
        }
        if(r.equals(0)) {
            r = this.valor3().compareTo(other.valor3());
        }
        else {
            return r;
        }
        if(r.equals(0)) {
            r = this.valor4().compareTo(other.valor4());
        }
        else {
            return r;
        }
        if(r.equals(0)) {
            r = this.valor5().compareTo(other.valor5());
        }
        else {
            return r;
        }

        return r;
    }

}
