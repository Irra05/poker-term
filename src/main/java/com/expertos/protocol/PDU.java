package com.expertos.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PDU {
    public static enum MessageType {
        MESSAGE, REQUEST, RESPONSE, PING, ACK, NACK
    }

    private MessageType type;
    private Long messageNum;
    private Long ackNum;
    private String content;

    public PDU(MessageType type, Long messageNum, Long ackNum, String content) {
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

    public String getJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getJsonAsBytes() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsBytes(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
