package com.expertos.protocol.runnables;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive implements Runnable{
    DatagramSocket socket;
    byte[] buffer;

    public Receive(byte[] buffer, Integer port) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        DatagramPacket message = new DatagramPacket(buffer, buffer.length);

        try {
            this.socket.receive(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
