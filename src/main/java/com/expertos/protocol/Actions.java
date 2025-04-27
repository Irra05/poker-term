package com.expertos.protocol;

import com.expertos.protocol.runnables.Receive;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Actions {

    // No sé si esto es necesario
    public static Integer serverPort = 12099;

    public static Integer setServerPort(Integer port) {
        serverPort = port;

        return port;
    }

    public static Response receiveResponse(DatagramSocket senderSocket, Long expectedAckNum, InetAddress address, Integer port) throws IOException {
        DatagramPacket responsePacket = new DatagramPacket(new byte[5000], 5000, address, port);
        DatagramPacket ackPacket;
        ObjectMapper mapper = new ObjectMapper();

        senderSocket.receive(responsePacket);
        PDU responsePdu = mapper.readValue(responsePacket.getData(), PDU.class);

        if(!responsePdu.getAckNum().equals(expectedAckNum)) {

        }

        PDU ack;

        return null;
    }

    public static Response sendRequest(byte[] requestPdu, DatagramSocket senderSocket, InetAddress address, Integer port) {
        Response response = null;

        DatagramPacket packet = new DatagramPacket(requestPdu, requestPdu.length, address, port);



        try {
            senderSocket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

}
