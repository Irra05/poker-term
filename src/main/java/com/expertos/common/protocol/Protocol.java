package com.expertos.common.protocol;

import java.util.Optional;

public class Protocol {
    public static enum MessageType {
        CONNECT, DISCONNECT, ACK, NACK, PING, MESSAGE, REQUEST, RESPONSE
    }

    private MessageType type;
    private Long messageNum;
    private Long ackNum;
    private String content;
}
