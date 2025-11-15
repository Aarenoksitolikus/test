package ru.itis.oris.test.util;

import ru.itis.oris.test.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {
    Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTicket(Book book) throws SQLException {
        String sql = "insert into tickets (title, author, available) values (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.getAvailable());
            ps.executeUpdate();
        }
    }
}
