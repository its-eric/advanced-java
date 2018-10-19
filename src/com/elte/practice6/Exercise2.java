package com.elte.practice6;

import java.util.stream.IntStream;

public class Exercise2 {

    /**
     * Make a server to which any number of clients can connect. Whenever a client sends a text message,
     * it is immediately sent to all other (currently connected) clients.
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread server = new Thread(new RobustServer());
        server.start();
        Thread.sleep(500);

//        IntStream.range(1, 15).forEach(n -> {
//            new Thread(new Client()).start();
//        });
    }
}
