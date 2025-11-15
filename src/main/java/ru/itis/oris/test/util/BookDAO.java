package ru.itis.oris.test.util;

import ru.itis.oris.test.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void createBook(Book book) throws SQLException {
        String sql = "insert into books (title, author, available) values (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.getAvailable());
            ps.executeUpdate();
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "select * from books order by id";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)
        ) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                ));
            }
        }

        return books;
    }

    public void deleteBook(String title, String author) throws SQLException {
        String sql = "DELETE FROM books WHERE title = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
