package ru.itis.oris.test.dao;


import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.model.BorrowRecord;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDao {

    public void borrowBook(Integer bookId, Integer userId) {
        String sql = "INSERT INTO borrow_records (book_id, user_id, borrow_date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            stmt.setInt(2, userId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(Integer recordId) {
        String sql = "UPDATE borrow_records SET return_date = ? WHERE id = ? AND return_date IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BorrowRecord> findActiveByUserId(Integer userId) {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT br.*, b.title, b.author FROM borrow_records br " +
                "JOIN books b ON br.book_id = b.id " +
                "WHERE br.user_id = ? AND br.return_date IS NULL " +
                "ORDER BY br.borrow_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BorrowRecord record = new BorrowRecord();
                record.setId(rs.getInt("id"));
                record.setBookId(rs.getInt("book_id"));
                record.setUserId(rs.getInt("user_id"));
                record.setBorrowDate(rs.getDate("borrow_date").toLocalDate());

                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                record.setBook(book);

                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<BorrowRecord> findAllActive() {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT br.*, b.title, b.author, u.username FROM borrow_records br " +
                "JOIN books b ON br.book_id = b.id " +
                "JOIN users u ON br.user_id = u.id " +
                "WHERE br.return_date IS NULL " +
                "ORDER BY br.borrow_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BorrowRecord record = new BorrowRecord();
                record.setId(rs.getInt("id"));
                record.setBookId(rs.getInt("book_id"));
                record.setUserId(rs.getInt("user_id"));
                record.setBorrowDate(rs.getDate("borrow_date").toLocalDate());

                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                record.setBook(book);

                User user = new User();
                user.setUsername(rs.getString("username"));
                record.setUser(user);

                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public boolean isBookBorrowed(Integer bookId) {
        String sql = "SELECT COUNT(*) FROM borrow_records WHERE book_id = ? AND return_date IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}