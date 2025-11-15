package ru.itis.oris.test.dao;

import ru.itis.oris.test.config.ConnectionManager;
import ru.itis.oris.test.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDao {

    public void save(Post post) {
        String sql = "INSERT INTO posts (title, content, author_id, created_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getAuthorId());
            stmt.setTimestamp(4, Timestamp.valueOf(post.getCreatedDate()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        post.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении поста", e);
        }
    }

    public Optional<Post> findById(int id) {
        String sql = """
            SELECT p.*, u.username as author_username
            FROM posts p
            JOIN users u ON p.author_id = u.id
            WHERE p.id = ?
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapPost(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске поста", e);
        }

        return Optional.empty();
    }

    public List<Post> findAll() {
        String sql = """
            SELECT p.*, u.username as author_username
            FROM posts p
            JOIN users u ON p.author_id = u.id
            ORDER BY p.created_date DESC
            """;

        List<Post> posts = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                posts.add(mapPost(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка постов", e);
        }

        return posts;
    }

    public List<Post> findByAuthorId(int authorId) {
        String sql = """
            SELECT p.*, u.username as author_username
            FROM posts p
            JOIN users u ON p.author_id = u.id
            WHERE p.author_id = ?
            ORDER BY p.created_date DESC
            """;

        List<Post> posts = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, authorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                posts.add(mapPost(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске постов автора", e);
        }

        return posts;
    }

    public void update(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении поста", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM posts WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении поста", e);
        }
    }

    public boolean isAuthor(int postId, int userId) {
        String sql = "SELECT author_id FROM posts WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("author_id") == userId;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при проверке авторства", e);
        }

        return false;
    }

    private Post mapPost(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setAuthorId(rs.getInt("author_id"));
        post.setAuthorUsername(rs.getString("author_username"));
        post.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        return post;
    }
}
