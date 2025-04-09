package com.expertos.pruebas;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpPrueba {
    DatagramSocket server;
    DatagramSocket client;

    {
        try {
            server = new DatagramSocket(12099);
            client = new DatagramSocket(12345);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    byte[] buffer = "Hola mundo".getBytes();

    DatagramPacket mesage = new DatagramPacket(buffer, buffer.length);


}
