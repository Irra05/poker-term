package com.expertos;

import com.expertos.poker.PokerGame;

public class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.println("========================================");
            System.out.println("        Bienvenido a Poker Term");
            System.out.println("========================================");

            PokerGame.start();
            //break;
        }
    }
}
