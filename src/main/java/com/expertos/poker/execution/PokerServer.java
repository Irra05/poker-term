package com.expertos.poker.execution;

public class PokerServer {
    private Boolean enablePrints;

    public PokerServer(Boolean enablePrints) {
        this.enablePrints = enablePrints;
    }

    private <E> void printIfEnabled(E x) {
        if(enablePrints)
            System.out.println(x);
    }

    public void start() {
        printIfEnabled("Poker server");
    }
}
