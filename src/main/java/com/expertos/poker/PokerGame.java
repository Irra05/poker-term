package com.expertos.poker;

import com.expertos.poker.runnables.PokerServerAsync;

import java.util.Scanner;

public class PokerGame {
    private static void selectOption(String s) {
        s = s.toLowerCase();

        switch(s) {
            case "unirse": {
                PokerClient.start();
                break;
            }
            case "crear": {
                Thread server = new Thread(new PokerServerAsync(false));
                server.start();

                PokerClient.start();
                break;
            }
            case "host": {
                PokerServer pokerServer = new PokerServer(true);
                pokerServer.start();
                break;
            }
            default:
                System.out.println("el comando no es válido");
        }
    }

    public static void start() {
        Scanner userInput = new Scanner(System.in);
        String option = "";

        System.out.println("\n¿Qué quieres hacer?\n");

        System.out.println("""
                Posibles comandos:
                  - "unirse": Unirse a una mesa existente.
                  - "crear": Crear una mesa.
                  - "host": Hostear una mesa.""");
        do {
            System.out.print("Escribe un comando -> ");
            option = userInput.nextLine();
            selectOption(option);

        } while(!option.equalsIgnoreCase("unirse") &&
                !option.equalsIgnoreCase("crear") &&
                !option.equalsIgnoreCase("host"));
    }
}
