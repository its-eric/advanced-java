package com.elte.practice5;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CuriousClient implements Runnable {
    @Override
    public void run() {
        try(
                Socket s = new Socket("localhost", 23456);
                Scanner scan = new Scanner(s.getInputStream())
        ) {
            int howMany = scan.nextInt();
            System.out.println("I'm curious client no. " + Thread.currentThread().getId() +
                    " and the server told me " + howMany + " people have connected so far!");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
