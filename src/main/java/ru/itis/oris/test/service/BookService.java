package ru.itis.oris.test.service;

import ru.itis.oris.test.util.BookDAO;
import ru.itis.oris.test.util.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = bookDAO;
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
