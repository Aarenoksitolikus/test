package ru.itis.oris.test.model;

import lombok.Data;

@Data
public class CommentEntity {
    private Long id;
    private Long post_id;
    private Long user_id;
    private String content;
    private String created_date;
}
