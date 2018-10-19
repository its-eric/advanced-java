package com.elte.practice6;

import com.elte.practice5.Practice5;
import com.elte.practice5.Recursive;
import com.elte.practice5.RecursiveFibonacci;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class ThreadFibonacciServer implements Runnable {

    @Override
    public void run() {
        System.out.println("Server is running and awaiting connection...");
        try (
                ServerSocket ss = new ServerSocket(12345)
        )
        {
            while(true) {
                handleClient(ss);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(ServerSocket ss) {
        try(
                Socket s = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream())
        ) {
            int n = ois.readInt();
            oos.writeInt(fib(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int fib(int n) {
        if(n == 0) return 1;
        else if(n == 1) return 1;
        else if(n > 1) {
            return fib(n -1) + fib(n - 2);
        } else {
            throw new ArithmeticException("Cannot calculate negative Fib!");
        }
    }
}
