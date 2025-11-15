package ru.itis.oris.test.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDaoImpl implements PostDao {
    private final Connection connection;

    public PostDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT id, title, author_id, content, created_date FROM posts ORDER BY created_date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                posts.add(new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("content"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }

    @Override
    public Optional<Post> findById(Long id) {
        String sql = "SELECT id, title, author_id, content, created_date FROM posts WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Post post = new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("content"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                );
                return Optional.of(post);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO posts(title, author_id, content, created_date) VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getTitle());
            stmt.setLong(2, post.getAuthorId());
            stmt.setString(3, post.getContent());
            stmt.setTimestamp(4, Timestamp.valueOf(post.getCreatedDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
