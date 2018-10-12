package com.elte.practice5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Make a simple client-server application whose parts communicate using serialization.
 * Use the Book and BookStore classes from the previous exercise.
 */
public class BookServer implements Runnable {

    @Override
    public void run() {
        System.out.println("Server is running and awaiting connection...");
        try (
            ServerSocket ss = new ServerSocket(12345)
        )
        {
            while(true) {
                handleClient(ss);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(ServerSocket ss) {
        try(
            Socket s = ss.accept();
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream())
        ) {
            // Takes the name of a book from the client.
            String title = (String) ois.readObject();

            // Tries to load the books from a file.
            BookStore bookStore = new BookStore();
            if(! bookStore.loadBooks("test.txt")) {
                // If unsuccessful, the store is initialised
                bookStore.addSomeBooks();
            }

            /**
             * Looks up the requested book by name in the store.
             * If found, sends true to the client, then the book itself.
             * Otherwise, sends false to the client.
             */
            Book requestedBook = bookStore.bookStore.get(title);
            if(requestedBook == null) {
                oos.writeBoolean(false);
            } else {
                oos.writeBoolean(true);
                oos.writeObject(requestedBook);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
