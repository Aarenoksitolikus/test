package ru.itis.oris.test.model;

import ru.itis.oris.test.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDao {

    public void borrowBook(Integer bookId, Integer userId) {
        String sql = "INSERT INTO borrowrecords (book_id, user_id, borrow_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, userId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();

            String updateSql = "UPDATE books SET available = false WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(Integer recordId) {
        String sql = "UPDATE borrowrecords SET return_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, recordId);
            stmt.executeUpdate();

            String selectSql = "SELECT book_id FROM borrowrecords WHERE id = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setInt(1, recordId);
                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    int bookId = rs.getInt("book_id");
                    String updateSql = "UPDATE books SET available = true WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, bookId);
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BorrowRecord> findCurrentBorrowsByUser(Integer userId) {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = """
            SELECT br.id, br.book_id, br.user_id, br.borrow_date, br.return_date, 
                   b.title as book_title, u.username as user_name
            FROM borrowrecords br
            JOIN books b ON br.book_id = b.id
            JOIN users u ON br.user_id = u.id
            WHERE br.user_id = ? AND br.return_date IS NULL
            ORDER BY br.borrow_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(BorrowRecord.builder()
                        .id(rs.getInt("id"))
                        .bookId(rs.getInt("book_id"))
                        .userId(rs.getInt("user_id"))
                        .borrowDate(rs.getDate("borrow_date").toLocalDate())
                        .returnDate(rs.getDate("return_date") != null ?
                                rs.getDate("return_date").toLocalDate() : null)
                        .bookTitle(rs.getString("book_title"))
                        .userName(rs.getString("user_name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}