package com.elte.practice5;

import java.util.stream.Stream;

public class Exercise1 implements Runnable {

    String text;

    public Exercise1 (String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (text){
                Stream.of(text).forEach(System.out::println);
            }
        }
    }
}
