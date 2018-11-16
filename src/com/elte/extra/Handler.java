package com.elte.extra;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Handler implements Runnable {

    private String folder;
    private Socket client;

    public Handler(Socket client, String folder) {
        this.client = client;
        this.folder = folder;
    }

    @Override
    public void run() {
        try (
            Scanner scanner = new Scanner(client.getInputStream());
            OutputStream clientOutputStream = client.getOutputStream()
        ) {
            if (scanner.hasNextLine()) {
                String fileName = scanner.nextLine();
                Path path = Paths.get(folder + "/" + fileName).toAbsolutePath();
                boolean fileOK = Files.exists(path) & Files.size(path) > 0L;

                if(fileOK) {
                    clientOutputStream.write(Files.readAllBytes(Paths.get(folder + "/" + fileName)));
                    clientOutputStream.flush();
                } else return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
