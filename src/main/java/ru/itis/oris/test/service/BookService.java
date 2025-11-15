package ru.itis.oris.test.service;


import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.dao.BookDao;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book getBookById(Integer id) {
        return bookDao.findById(id).orElse(null);
    }

    public void createBook(String title, String author) {
        Book book = new Book(title, author, true);
        bookDao.save(book);
    }

    public void updateBook(Integer id, String title, String author, Boolean available) {
        Book book = new Book(title, author, available);
        book.setId(id);
        bookDao.update(book);
    }

    public void deleteBook(Integer id) {
        bookDao.delete(id);
    }

    public boolean validateBookData(String title, String author) {
        return title != null && !title.trim().isEmpty() &&
                author != null && !author.trim().isEmpty();
    }
}