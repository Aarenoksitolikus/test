package ru.itis.oris.test.converter;

import ru.itis.oris.test.entity.BookEntity;
import ru.itis.oris.test.model.Book;

public class BookConverter implements Converter<Book, BookEntity> {
    @Override
    public BookEntity convert(Book model) {
        return BookEntity.builder()
                .id(model.getId())
                .title(model.getTitle())
                .author(model.getAuthor())
                .available(model.isAvailable())
                .build();
    }
}
