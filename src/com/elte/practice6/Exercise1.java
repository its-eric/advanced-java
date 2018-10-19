package com.elte.practice6;

public class Exercise1 {

    /**
     * Make a TCP/IP server that can handle several clients: after accepting them, each connection is handled by a separate thread.
     * The program works like this: the client sends a number, and the server replies with the corresponding Fibonacci number.
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadFibonacciServer fibonacciServer = new ThreadFibonacciServer();
        Thread server = new Thread(fibonacciServer);

        server.start();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new ThreadFibonacciClient());
            t.start();
            t.join();
        }
    }

}
