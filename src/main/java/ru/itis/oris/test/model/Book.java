package ru.itis.oris.test.model;

import lombok.Getter;

@Getter
public class Book {
    long id;
    String title;
    String author;
    Boolean available;

    public Book(long id, String title, String author, Boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public Book(String title, String author, Boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }
}
