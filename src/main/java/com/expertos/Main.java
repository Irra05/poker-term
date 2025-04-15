package com.expertos;

import com.expertos.poker.PokerInit;

public class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.println("========================================");
            System.out.println("        Bienvenido a Poker Term");
            System.out.println("========================================");

            PokerInit.start();
            //break;
        }
    }
}
