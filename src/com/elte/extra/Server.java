package com.elte.extra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Please give a port number as the first argument and a folder as the second!");
        } else {
            try{
                int port = Integer.parseInt(args[0]);
                String folder = String.valueOf(args[1]);
                handleClients(port, folder);
            } catch (NumberFormatException ne) {
                System.out.println("Please give a port number between as the first argument!");
            }
        }
    }

    private static void handleClients(int port, String folder) {
        try (ServerSocket ss = new ServerSocket(port)) {
            while(true) {
                Socket client = ss.accept();
                new Thread(new Handler(client, folder)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
