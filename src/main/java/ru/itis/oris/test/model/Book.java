package ru.itis.oris.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Book {
    private Integer id;
    private String title;
    private String author;
    private boolean available;
}
