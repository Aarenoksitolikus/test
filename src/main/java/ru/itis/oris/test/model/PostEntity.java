package ru.itis.oris.test.model;

import lombok.Data;

@Data
public class PostEntity {
    private Long id;
    private String title;
    private String author_id;
    private String authorName;
    private String content;
    private String created_date;
}