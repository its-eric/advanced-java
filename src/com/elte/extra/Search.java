package com.elte.extra;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class Search {

    public static void main(@NotNull String[] args) {

        try {
            Stream<Path> paths = Files.walk(FileSystems.getDefault().getPath("."))
                                        .filter(path -> ! path.toFile().isDirectory());
            String searchTerm = args[0];
            paths.forEach(p -> {
                Thread t = new Thread(new SearchThread(p, searchTerm));
                t.start();
            });
            paths.close();
        }  catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a search term for the program to work properly.");
        }  catch(IOException e) {
            System.out.println("There was a problem reading the files from the current directory!");
        }
    }
}
