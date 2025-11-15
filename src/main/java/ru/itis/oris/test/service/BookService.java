package ru.itis.oris.test.service;

import lombok.RequiredArgsConstructor;
import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.repository.BookRepository;

import java.util.List;

@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAllByUserId(Integer id) {
        return bookRepository.findAllByUserId(id);
    }

}
