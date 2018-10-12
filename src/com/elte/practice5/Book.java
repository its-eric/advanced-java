package com.elte.practice5;

import java.io.Serializable;

public class Book implements Serializable {

    String title;
    String author;
    int year;
    int numPages;

    public Book(String title, String author, int year, int numPages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.numPages = numPages;
    }
}
