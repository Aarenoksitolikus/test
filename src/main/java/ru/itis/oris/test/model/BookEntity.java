package ru.itis.oris.test.model;

import lombok.*;

@Getter
@Setter
@Builder
public class BookEntity {
    private Long id;
    private String title;
    private String author;
    private String available;
}
