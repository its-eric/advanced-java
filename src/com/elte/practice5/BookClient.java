package com.elte.practice5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class BookClient {

    public static void main(String[] args) {
        try (
            Socket s = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream())
        ) {
            oos.writeObject("20 Thousand Leagues Under The Sea");
            oos.flush();
            if(ois.readObject().equals("true")) {
                Book book = (Book) ois.readObject();
                System.out.println("Got the book " + book.title);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
