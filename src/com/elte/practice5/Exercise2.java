package com.elte.practice5;

import java.util.stream.Stream;

public class Exercise2 implements Runnable {

    String text;

    public Exercise2 (String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (text) {
                Stream.of(text.toCharArray()).forEach(System.out::println);
            }
        }
    }

}
