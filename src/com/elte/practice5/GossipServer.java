package com.elte.practice5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GossipServer implements Runnable {

    private static int connectedPeople = 0;

    @Override
    public void run() {
        System.out.println("Server is running and awaiting connection...");
        try(ServerSocket ss = new ServerSocket(23456)) {
            while(true) {
                try(
                    Socket s = ss.accept();
                    PrintWriter pw = new PrintWriter(s.getOutputStream());
                ) {
                    connectedPeople++;
                    pw.println(connectedPeople);
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
