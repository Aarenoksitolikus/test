package ru.itis.oris.test.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/controlWork",
                "postgres", "Uekz2005");
    }

    public void save(PostEntity post) throws SQLException {
        String sql = "INSERT INTO posts (title, author_id, content, created_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, post.getTitle());
            stmt.setLong(2, Long.parseLong(post.getAuthor_id()));
            stmt.setString(3, post.getContent());
            stmt.setTimestamp(4, Timestamp.valueOf(post.getCreated_date()));
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public List<PostEntity> findAll() throws SQLException {
        String sql = "SELECT p.*, u.username as author_name FROM posts p " +
                "JOIN users u ON p.author_id = u.id " +
                "ORDER BY p.created_date DESC";
        List<PostEntity> posts = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PostEntity post = new PostEntity();
                post.setId(rs.getLong("id"));
                post.setTitle(rs.getString("title"));
                post.setAuthor_id(rs.getString("author_id"));
                post.setAuthorName(rs.getString("author_name"));
                post.setContent(rs.getString("content"));
                post.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime().toString());
                posts.add(post);
            }
        }
        return posts;
    }

    public PostEntity findById(Long id) throws SQLException {
        String sql = "SELECT p.*, u.username as author_name FROM posts p " +
                "JOIN users u ON p.author_id = u.id " +
                "WHERE p.id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PostEntity post = new PostEntity();
                    post.setId(rs.getLong("id"));
                    post.setTitle(rs.getString("title"));
                    post.setAuthor_id(rs.getString("author_id"));
                    post.setAuthorName(rs.getString("author_name"));
                    post.setContent(rs.getString("content"));
                    post.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime().toString());
                    return post;
                }
            }
        }
        return null;
    }

    public void update(PostEntity post) throws SQLException {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
