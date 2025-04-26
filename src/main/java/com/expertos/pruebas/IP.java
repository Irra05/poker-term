package com.expertos.pruebas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.*;

public class IP {

    public static class ReceiveWithoutBlocking implements Runnable {
        DatagramSocket socket;
        DatagramPacket mensaje;

        public ReceiveWithoutBlocking(DatagramPacket mensaje, Integer port) throws SocketException {
            this.socket = new DatagramSocket(port);
            this.mensaje = mensaje;
        }

        @Override
        public void run() {

            try {
                this.socket.receive(mensaje);
                System.out.println("mensaje.getAddress()");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
        // Esta es la de Ethernet
        // Necesitamos la de wifi
        // Olvídalo, sí funciona

        DatagramSocket client;

        ObjectMapper mapper = new ObjectMapper();

        String jsonContent = """
                {
                    "nombre": "Israel Sánchez Cabrera",
                    "edad": "19",
                    "email": "mecago"
                }
                """;
        byte[] sendMessage = jsonContent.getBytes();

        try {
            client = new DatagramSocket();
            byte[] response = new byte[100];
            DatagramPacket responsePacket = new DatagramPacket(response, response.length);

            Thread receive = new Thread(new ReceiveWithoutBlocking(responsePacket, 12099));
            receive.start();

            DatagramPacket datagram = new DatagramPacket(
                    sendMessage,
                    sendMessage.length,
                    InetAddress.getByName("255.255.255.255"),
                    12099);

            client.send(datagram);

            receive.join();

            System.out.println(responsePacket.getAddress());
            System.out.println(mapper.readValue(new String(response), Persona.class));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
