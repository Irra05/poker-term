package com.expertos.protocol;

public record Request(Verb verb, String route, String body) {

    public static enum Verb {
        CALL, GET, POST, PUT, DELETE
    }

}
