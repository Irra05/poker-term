package com.expertos.poker.runnables;

import com.expertos.poker.execution.PokerServer;

public class PokerServerAsync implements Runnable{

    PokerServer pokerServer;

    public PokerServerAsync(Boolean enablePrints) {
        this.pokerServer = new PokerServer(enablePrints);
    }
    @Override
    public void run() {
        pokerServer.start();
    }
}
