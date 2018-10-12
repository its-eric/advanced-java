package com.elte.practice5;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// @param <I> - Functional Interface Type
class Recursive<I> {
    public I func;
}

public class Practice5 {

    public static void main(String[] args) throws InterruptedException {
        Exercise1 runnable1 = new Exercise1("I'm thread number 1");
        Exercise1 runnable2 = new Exercise1("I'm thread number 2");
        Thread t1 = new Thread(runnable1);
        t1.start();
        Thread t2 = new Thread(runnable2);
        t2.start();

        t1.join();
        t2.join();

        Recursive<Function<Integer, Long>> fib = new Recursive<>();

        fib.func = n -> {
            if(n == 0) return 0L;
            else if(n == 1) return 1L;
            else {
                return fib.func.apply(n -1 ) + fib.func.apply(n -2);
            }

        };
        System.out.println(fib.func.apply(5));

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
