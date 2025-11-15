package ru.itis.oris.test.model;

import ru.itis.oris.test.model.dao.PostDao;
import ru.itis.oris.test.model.entities.Post;
import ru.itis.oris.test.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {

    @Override
    public Post findById(long id) {
        String sql = "SELECT id, title, content, author_id, created_date FROM post WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getLong("author_id"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка findById", e);
        }

        return null;
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT id, title, content, author_id, created_date FROM post ORDER BY created_date DESC";
        List<Post> posts = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                posts.add(new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getLong("author_id"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка findAll", e);
        }

        return posts;
    }

    @Override
    public Post save(Post post) {
        String sql = "INSERT INTO post(title, content, author_id, created_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getAuthorId());
            stmt.setTimestamp(4, Timestamp.valueOf(post.getCreatedDate()));

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                post.setId(keys.getLong(1));
            }

            return post;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка save", e);
        }
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка update", e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM post WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка delete", e);
        }
    }
}
