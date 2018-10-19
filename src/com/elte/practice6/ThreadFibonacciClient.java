package com.elte.practice6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadFibonacciClient implements Runnable {

    @Override
    public void run() {
        try (
                Socket s = new Socket("localhost", 12345);
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream())
        ) {
            int n = ThreadLocalRandom.current().nextInt(0, 21);
            oos.writeInt(n);
            oos.flush();
            int result = ois.readInt();
            System.out.println("The result for " + n + " is " + result);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}