package ru.itis.oris.test.service;

import lombok.AllArgsConstructor;
import ru.itis.oris.test.repository.BookRepository;

@AllArgsConstructor
public class BorrowService {

    private final BookRepository bookRepository;

    public boolean borrow(Integer bookId) {
        return false;
    }

}
