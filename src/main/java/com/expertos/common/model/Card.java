package com.expertos.common.model;

import java.util.Objects;

public class Card {

    public static enum Suit {
        SPADES, CLUBS, DIAMONDS, HEARTS
    }

    protected Integer num;
    protected Suit suit;

    public Card(Integer num, Suit suit) {
        if(num < 1 || num > 13) {
            throw new IllegalArgumentException("Card number must be between 1 and 13");
        }

        this.num = num;
        this.suit = suit;
    }

    public Integer getNum() {
        return num;
    }

    public String getNumRight() {
        String res = null;
        switch(num) {
            case 1: {
                res = "A";
                break;
            }
            case 11: {
                res = "J";
                break;
            }
            case 12: {
                res = "Q";
                break;
            }
            case 13: {
                res = "K";
                break;
            }
            default: {
                res = getNum().toString();
            }
        }

        return res;
    }

    public Suit getSuit() {
        return suit;
    }

    public Character getSuitRight() {
        Character res;

        switch(suit) {
            case SPADES: {
                res = '♠';
                break;
            }
            case CLUBS: {
                res = '♣';
                break;
            }
            case DIAMONDS: {
                res = '♦';
                break;
            }
            case HEARTS: {
                res = '♥';
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + suit);
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card that = (Card) o;
        return Objects.equals(num, that.num) && suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, suit);
    }

    @Override
    public String toString() {
        return getNum() + ", " + getSuit();
    }

}
