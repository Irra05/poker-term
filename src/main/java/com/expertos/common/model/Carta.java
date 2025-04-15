package com.expertos.common.model;

import java.util.Objects;

public class Carta {

    public static enum Palo {
        PICAS, TREBOLES, DIAMANTES, CORAZONES, COMODIN
    }

    public static enum Color {
        NEGRO, ROJO, COMODIN
    }

    protected Integer num;
    protected Palo palo;

    public Carta(Integer num, Palo palo) {
        if(num < 1 || num > 13) {
            throw new IllegalArgumentException("Card number must be between 1 and 13");
        }

        this.num = num;
        this.palo = palo;
    }

    public Integer getNum() {
        return num;
    }

    public Character getNumRight() {
        Character res = null;
        switch(num) {
            case 1: {
                res = 'A';
                break;
            }
            case 11: {
                res = 'J';
                break;
            }
            case 12: {
                res = 'Q';
                break;
            }
            case 13: {
                res = 'K';
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + num);
            }
        }

        return res;
    }

    public Palo getPalo() {
        return palo;
    }

    public Character getPaloRight() {
        Character res;

        switch(palo) {
            case PICAS: {
                res = '♠';
                break;
            }
            case TREBOLES: {
                res = '♣';
                break;
            }
            case DIAMANTES: {
                res = '♦';
                break;
            }
            case CORAZONES: {
                res = '♥';
                break;
            }
            case COMODIN: {
                res = 'C';
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + palo);
        }

        return res;
    }

    public Color getColor() {
        Color res = null;

        switch(getPalo()) {
            case PICAS: {}
            case TREBOLES: {
                res = Color.NEGRO;
                break;
            }
            case DIAMANTES: {}
            case CORAZONES: {
                res = Color.ROJO;
                break;
            }
            default: res = Color.COMODIN;
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carta that = (Carta) o;
        return Objects.equals(num, that.num) && palo == that.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, palo);
    }

    @Override
    public String toString() {
        return getNum() + ", " + getPalo();
    }

}
