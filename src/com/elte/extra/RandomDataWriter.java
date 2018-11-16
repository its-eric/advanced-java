package com.elte.extra;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RandomDataWriter {
    public static void main(String[] args) {
        try {
            String fileName = args[0];
            Path path = Paths.get(fileName);

        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please give a filename (relative to the working dir) as an argument " +
                                "for this program to work.");
        }
    }
}
