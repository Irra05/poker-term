package com.expertos.pruebas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.*;

public class UdpPrueba {
    public static class ReceiveWithoutBlocking implements Runnable {
        DatagramSocket socket;
        byte[] buffer;

        public ReceiveWithoutBlocking(byte[] buffer, Integer port) throws SocketException {
            this.socket = new DatagramSocket(port);
            this.buffer = buffer;
        }

        @Override
        public void run() {
            DatagramPacket mensaje = new DatagramPacket(buffer, buffer.length);

            try {
                this.socket.receive(mensaje);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
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

            Thread receive = new Thread(new ReceiveWithoutBlocking(response, 12099));
            receive.start();

            DatagramPacket datagram = new DatagramPacket(
                    sendMessage,
                    sendMessage.length,
                    InetAddress.getByName("127.0.0.1"),
                    12099);

            client.send(datagram);

            receive.join();

            System.out.println(mapper.readValue(new String(response), Persona.class));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();;
        }
    }


}
