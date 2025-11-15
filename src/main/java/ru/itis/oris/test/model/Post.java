package ru.itis.oris.test.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Post {
    private Integer id;
    private String title;
    private String content;
    private Integer authorId;
    private String authorName;
    private LocalDateTime createdDate;
}