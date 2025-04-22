package com.expertos.common.model;

import java.util.Objects;

public class Card {

    public static enum Color {
        PICAS, TREBOLES, DIAMANTES, CORAZONES
    }

    protected Integer num;
    protected Color color;

    public Card(Integer num, Color color) {
        if(num < 1 || num > 13) {
            throw new IllegalArgumentException("Card number must be between 1 and 13");
        }

        this.num = num;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    public Character getColorRight() {
        Character res;

        switch(color) {
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
            default:
                throw new IllegalStateException("Unexpected value: " + color);
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card that = (Card) o;
        return Objects.equals(num, that.num) && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, color);
    }

    @Override
    public String toString() {
        return getNum() + ", " + getColor();
    }

}
