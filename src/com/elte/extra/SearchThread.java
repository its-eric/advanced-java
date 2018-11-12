package com.elte.extra;

import java.io.*;
import java.nio.file.Path;

public class SearchThread implements Runnable {

    private Path path;
    private String searchTerm;

    SearchThread(Path path, String searchTerm) {
        this.path = path;
        this.searchTerm = searchTerm;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(path.toAbsolutePath().toString()))
        ) {
            String line;
            while((line = reader.readLine()) != null) {
                if(line.contains(searchTerm))
                    System.out.println(path.toString() + " : " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not open a file with the following name, please check: " + path.toAbsolutePath().toString());
        }
    }
}
