package ru.itis.oris.test.model;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Boolean available;

    public Book() {}

    public Book(Integer id, String title, String author, Boolean available) {
        this.id = id;
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

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private Integer id;
        private String title;
        private String author;
        private Boolean available;

        public BookBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder available(Boolean available) {
            this.available = available;
            return this;
        }

        public Book build() {
            return new Book(id, title, author, available);
        }
    }
}