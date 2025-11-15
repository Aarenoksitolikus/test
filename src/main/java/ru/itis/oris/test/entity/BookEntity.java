package ru.itis.oris.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookEntity {
    private Integer id;
    private String title;
    private String author;
    private boolean available;
}
