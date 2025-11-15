package ru.itis.oris.test.repository;

import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.util.ConnectionManager;
import ru.itis.oris.test.util.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public List<Book> findAll() {
        String query = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Book book = ResultSetConverter.convertToBook(rs);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Book> findAllByUserId(Integer id) {
        String query = """
                SELECT  b.id,
                        title,
                        author,
                        available        
                FROM books b JOIN borrowrecords br ON b.id = br.book_id
                JOIN users u ON br.user_id = u.id
                WHERE u.id = ?
                """;
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = ResultSetConverter.convertToBook(rs);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
