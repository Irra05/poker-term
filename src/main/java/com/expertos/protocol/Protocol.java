package com.expertos.protocol;

import java.util.Optional;

public class Protocol {
    public static enum MessageType {
        CONNECT, DISCONNECT, START, FINISH, ACK, NACK, PING, MESSAGE, REQUEST, RESPONSE
    }

    private MessageType type;
    private Long messageNum;
    private Long ackNum;
    private String content;

    public Protocol(MessageType type, Long messageNum, Long ackNum, String content) {
        this.type = type;
        this.messageNum = messageNum;
        this.ackNum = ackNum;
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public Long getMessageNum() {
        return messageNum;
    }

    public Long getAckNum() {
        return ackNum;
    }

    public String getContent() {
        return content;
    }
}
