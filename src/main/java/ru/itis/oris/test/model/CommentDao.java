package ru.itis.oris.test.model;

import java.sql.*;

public class CommentDao {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/controlWork",
                "postgres", "Uekz2005");
    }

    private Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("ваще всё поломалось, Наташа, мы ваще всё уронили");
            throw new IllegalStateException(e);
        }
    }

    public void save(PostEntity post) throws SQLException {
        String sql = "INSERT INTO courses (id, title, author_id, content, created_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, post.getId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getAuthor_id());
            stmt.setString(4, post.getContent());
            stmt.setDouble(5, post.getCreated_date());
            stmt.executeUpdate();
        }
    }
}
