package com.expertos.poker.execution;

import com.expertos.poker.execution.client.PokerClient;
import com.expertos.poker.execution.server.PokerServer;
import com.expertos.poker.runnables.PokerServerAsync;

import java.util.Scanner;

public class PokerInit {
    private static void selectOption(String s) {
        s = s.toLowerCase();

        switch(s) {
            case "unirse": {
                PokerClient client = new PokerClient();
                client.start();
                break;
            }
            case "crear": {
                Thread server = new Thread(new PokerServerAsync(false));
                server.start();

                PokerClient client = new PokerClient("127.0.0.1");
                client.start();
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
