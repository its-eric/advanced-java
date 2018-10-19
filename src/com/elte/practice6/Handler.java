package com.elte.practice6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Handler implements Runnable {

    private final List<PrintWriter> clients;
    private final Socket s;

    public Handler(Socket s, List<PrintWriter> clients) {
        this.s = s;
        this.clients = clients;
    }

    @Override
    public void run() {
        try (
                Scanner scan = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream())
        ) {
            clients.add(pw);

            while (scan.hasNextLine()) {
                String clientMessage = scan.nextLine();

                for (PrintWriter pw2 : clients) {
                    pw2.println("Someone sent the message " + clientMessage);
                    pw2.flush();
                }
            }

            clients.remove(pw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
