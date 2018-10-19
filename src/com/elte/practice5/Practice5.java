package com.elte.practice5;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Practice5 {

//    public static final Function<Integer, Long> fibRecFunction = n -> {
//        if(n == 0) return 0L;
//        else if(n == 1) return 1L;
//        else {
//            return fib.func.apply(n -1 ) + fib.func.apply(n -2);
//        }
//    };

    public static void main(String[] args) throws InterruptedException {
        exercise1();

        exercise2();

        exercise3();

        exercise4();

        exercise5();
    }

    /**
     * Make two threads that each use System.out.println to print a given text 10000 times.
     * Give different texts to the threads.
     * Notice how the printouts are interleaved.
     *
     * @throws InterruptedException
     */
    private static void exercise1() throws InterruptedException {
        Exercise1 runnable1 = new Exercise1("I'm thread number 1");
        Exercise1 runnable2 = new Exercise1("I'm thread number 2");
        Thread t1 = new Thread(runnable1);
        t1.start();
        Thread t2 = new Thread(runnable2);
        t2.start();

        t1.join();
        t2.join();
    }

    /**
     * Now, instead of println, print each text character by character (and an ending newline).
     */
    private static void exercise2() {
        Exercise2 printerByCharacter = new Exercise2("I'm printing by character");
        Thread t3 = new Thread(printerByCharacter);
        t3.start();

        Exercise2 printerByCharacter2 = new Exercise2("I'm printing by character too");
        Thread t4 = new Thread(printerByCharacter2);
        t4.start();
    }

    /**
     * Compute the nth Fibonacci number so that the non-basic cases compute
     * the two halves of the addition on two separate threads. TODO
     */
    private static void exercise3() {
        Recursive<Function<Integer, Long>> fib = new Recursive<>();

        fib.func = n -> {
            if(n == 0) return 0L;
            else if(n == 1) return 1L;
            else {
                return fib.func.apply(n -1 ) + fib.func.apply(n -2);
            }
        };

        System.out.println(fib.func.apply(5));
    }

    /**
     * Bookstore server and client. Further comments are found in the relevant classes.
     *
     * @throws InterruptedException
     */
    private static void exercise4() throws InterruptedException {
        BookStore scienceFiction = new BookStore();
        scienceFiction.addSomeBooks();
        scienceFiction.saveBooks("test.txt");
        scienceFiction.loadBooks("test.txt");

        Thread server = new Thread(new BookServer());

        Thread bookClient1 = new Thread(new BookClient("Dune"));
        Thread bookClient2 = new Thread(new BookClient("20 Thousand Leagues Under The Sea"));
        Thread bookClient3 = new Thread(new BookClient("Neuromancer"));

        server.start();
        Thread.sleep(500);

        bookClient1.start();
        bookClient2.start();
        bookClient3.start();
    }

    /**
     * Create a server that remembers how many clients have connected to it so far,
     * and sends this number in a text message to the client.
     * The client receives this number and prints it, but it doesn’t send any messages to the server.
     *
     * @throws InterruptedException
     */
    private static void exercise5() throws InterruptedException {
        Thread gossipServer = new Thread(new GossipServer());
        gossipServer.start();
        Thread.sleep(500);

        IntStream.range(0, 100).forEach(
                n -> {
                    new Thread(new CuriousClient()).start();
                }
        );
    }
}
