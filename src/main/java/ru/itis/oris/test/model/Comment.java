package ru.itis.oris.test.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String userName;
    private String content;
    private LocalDateTime createdDate;
}