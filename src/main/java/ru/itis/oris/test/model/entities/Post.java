package ru.itis.oris.test.model.entities;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private Long authorId;
    private String content;
    private LocalDateTime createdDate;

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(long id, String title, String content, long authorId, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdDate = createdDate;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setTitle(String t) { this.title = t; }
    public void setContent(String c) { this.content = c; }

    public Long getAuthorId() {
        return authorId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
