package com.elte.extra;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileDownloader implements Runnable {

    private final String serverName;
    private final String file;

    public FileDownloader(String serverName, String file) {
        this.serverName = serverName;
        this.file = file;
    }

    @Override
    public void run() {
        String[] data = serverName.split(":");
        try (
                Socket s = new Socket(data[0], Integer.parseInt(data[1]));
                PrintWriter pw = new PrintWriter(s.getOutputStream())
        ) {
            pw.println(file);
            pw.flush();

            File targetFile = new File(file);
            Files.copy(s.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.out.println("Could not connect to the given host " + serverName + " to download file " + file + "!");
        }
    }
}
