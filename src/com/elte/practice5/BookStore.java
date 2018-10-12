package com.elte.practice5;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BookStore {

    Map<String, Book> bookStore = new HashMap<>();

    // Puts some predefined books into the map, to serve as initial data.
    public void addSomeBooks() {
        bookStore.put("20 Thousand Leagues Under The Sea",
                new Book(
                    "20 Thousand Leagues Under The Sea",
                    "Jules Verne",
                    1890,
                    500));
        bookStore.put("Dune",
                new Book(
                "Dune",
                "Frank Herbert",
                1956,
                700
        ));
    }

    // Writes the books into the given file using serialization.
    // Returns whether the save operation was successful.
    public boolean saveBooks(String fileName) {
        File file = new File(fileName);
        try (
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream ois = new ObjectOutputStream(outputStream)
        ) {
            ois.writeObject(this.bookStore);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    // Loads the books from the given file.
    // Returns whether the load operation was successful.
    public boolean loadBooks(String fileName) {
        File file = new File(fileName);
        try (
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(inputStream)
        ) {
            this.bookStore = (Map<String, Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

}
