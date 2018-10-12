package com.elte.practice5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BookClient implements Runnable {

    private String desiredBook;

    public BookClient(String desiredBook) {
        this.desiredBook = desiredBook;
    }

    @Override
    public void run() {
        try (
            Socket s = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream())
        ) {
            oos.writeObject(desiredBook);
            oos.flush();
            if(ois.readBoolean()) {
                Book book = (Book) ois.readObject();
                println("Got the book " + book.title);
            } else {
                println("Could not get my book... :(");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void println(String s) {
        System.out.println("Book client " + Thread.currentThread().getId() + " says:");
        System.out.println(s);
    }
}
