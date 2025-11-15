package ru.itis.oris.test.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String content;
    private int authorId;
    private String authorUsername;
    private LocalDateTime createdDate;

    // Конструкторы
    public Post() {}

    public Post(String title, String content, int authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdDate = LocalDateTime.now();
    }

    public Post(int id, String title, String content, int authorId, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdDate = createdDate;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // Вспомогательные методы
    public String getShortContent() {
        if (content == null) return "";
        if (content.length() <= 150) return content;
        return content.substring(0, 150) + "...";
    }

    public String getFormattedDate() {
        if (createdDate == null) return "";
        return createdDate.toString(); // Можно форматировать через DateTimeFormatter при необходимости
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", authorId=" + authorId +
            ", createdDate=" + createdDate +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
