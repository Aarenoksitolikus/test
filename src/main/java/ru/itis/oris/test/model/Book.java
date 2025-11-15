package ru.itis.oris.test.model;

import lombok.Getter;

@Getter
public class Book {
    long id;
    String title;
    String author;
    Boolean available;
}
