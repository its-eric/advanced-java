package com.elte.practice5;

import java.util.function.Function;

public class RecursiveFibonacci implements Runnable {

    Recursive<Function<Integer, Long>> rec;
    int n;

    public RecursiveFibonacci(Recursive<Function<Integer, Long>> rec, int n) {
        this.rec = rec;
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(rec.func.apply(n));
    }
}
