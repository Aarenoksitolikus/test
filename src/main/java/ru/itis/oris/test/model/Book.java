package ru.itis.oris.test.model;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Boolean available;

    public Book() {}

    public Book(String title, String author, Boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}