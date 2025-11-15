package ru.itis.oris.test.model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long postId;
    private Long userId;
    private String content;
    private LocalDateTime createdDate;

    public Comment() {}

    public Comment(Long id, Long postId, Long userId, String content, LocalDateTime createdDate) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdDate = createdDate;
    }
}
