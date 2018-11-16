package com.elte.extra;

import java.util.LinkedList;
import java.util.List;

public class Client {

    private static String serverName;
    private static List<String> files = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

        if(args.length >= 2 && args[0].split(":").length == 2) {
            serverName = args[0];

            for (int i = 1; i < args.length; i++) {
                files.add(args[i]);
            }

            for (String file: files) {
                Thread t1 = new Thread(new FileDownloader(serverName, file));
                t1.start();
                t1.join();
            }

        } else {
            System.out.println("This program takes at least 2 arguments, a server address and a filename to download.");
        }
    }

}
