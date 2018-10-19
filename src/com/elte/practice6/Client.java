package com.elte.practice6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {

    @Override
    public void run() {
        try(
                Socket s = new Socket("localhost", 12345);
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                Scanner scan = new Scanner(s.getInputStream())
        ) {
            pw.println("I'm client no. " + Thread.currentThread().getId() + "!");
            pw.flush();

            while(true) {
                String message = scan.nextLine();
                System.out.println("Client " + Thread.currentThread().getId() + " got the message: " +
                        message);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
