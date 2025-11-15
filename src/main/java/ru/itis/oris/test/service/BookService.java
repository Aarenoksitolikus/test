package ru.itis.oris.test.service;

import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.util.BookDAO;
import ru.itis.oris.test.util.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = bookDAO;
    }

    public List<Book> getBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public void createBook(Book book) throws SQLException {
        bookDAO.createBook(book);
    }

    public void deleteBook(Book book) throws SQLException {
        bookDAO.deleteBook(book.getTitle(), book.getAuthor());
    }

    public BookDAO createBookDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/oris",
                    "postgres",
                    "010909"
            );
            return new BookDAO(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
