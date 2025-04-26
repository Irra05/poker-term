package com.expertos.protocol;

public class Message {
    public static enum MessageType {
        MESSAGE, REQUEST, RESPONSE, PING, ACK, NACK
    }

    private MessageType type;
    private Long messageNum;
    private Long ackNum;
    private String content;

    public Message(MessageType type, Long messageNum, Long ackNum, String content) {
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
