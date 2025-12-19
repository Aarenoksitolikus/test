package ru.itis.oris.test.model;
import java.time.LocalDateTime;
public class Post {
    private Long id;
    private String title;
    private Long authorId;
    private String content;
    private LocalDateTime createdDate;

    public Post() {}

    public Post(Long id, String title, Long authorId, String content, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.createdDate = createdDate;
    }

    // get/set
}
