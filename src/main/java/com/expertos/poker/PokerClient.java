package com.expertos.poker;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PokerClient {

    private InetAddress address;

    public PokerClient(String address) {
        try {
            this.address = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Bro wtf are you doing with your dogshit-like life? " +
                    "this constructor is ment to connect to localhost and very rarely to a well known ip " +
                    "so stop doing any shit you were doing with this thing ok?");
        }
    }

    public PokerClient() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Introduce la IP del servidor al que te quieres conectar: -> ");
        try {
            this.address = InetAddress.getByName(userInput.nextLine());
        } catch (UnknownHostException e) {
            System.out.println("La IP está fuera del rango de IPs posibles (\"a.b.c.d\" donde a, b, c y d son números enteros entre 0 y 255 incluidos).");
        }
    }

    public void start() {
        System.out.println("Poker client");
    }
}
