package com.elte.practice6;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RobustServer implements Runnable {

    private List<PrintWriter> clients = new ArrayList<>();

    @Override
    public void run() {
        System.out.println("Server is running and awaiting connection...");
        try (
                ServerSocket ss = new ServerSocket(12345)
        )
        {
            while(true) {
                Socket client = ss.accept();
                new Thread(new Handler(client, clients)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
