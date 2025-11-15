package ru.itis.oris.test.dao;

import ru.itis.oris.test.model.Post;
import ru.itis.oris.test.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    public List<Post> findAll() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.*, u.username FROM posts p JOIN users u ON p.author_id = u.id ORDER BY p.created_date DESC";
        Connection conn = DatabaseUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Post po = new Post();
            po.setId(rs.getInt("id"));
            po.setTitle(rs.getString("title"));
            po.setContent(rs.getString("content"));
            po.setAuthorId(rs.getInt("author_id"));
            po.setAuthorName(rs.getString("username"));
            po.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
            posts.add(po);
        }
        return posts;
    }

    public void save(Post po) throws SQLException {
        String sql = "INSERT INTO posts (title, content, author_id) VALUES (?, ?, ?)";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, po.getTitle());
        stmt.setString(2, po.getContent());
        stmt.setInt(3, po.getAuthorId());
        stmt.executeUpdate();
    }

    public Post findById(int id) throws SQLException {
        String sql = "SELECT p.*, u.username FROM posts p JOIN users u ON p.author_id = u.id WHERE p.id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Post po = new Post();
            po.setId(rs.getInt("id"));
            po.setTitle(rs.getString("title"));
            po.setContent(rs.getString("content"));
            po.setAuthorId(rs.getInt("author_id"));
            po.setAuthorName(rs.getString("username"));
            po.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
            return po;
        }
        return null;
    }

    public void update(Post po) throws SQLException {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, po.getTitle());
        stmt.setString(2, po.getContent());
        stmt.setInt(3, po.getId());
        stmt.executeUpdate();
    }

    public boolean isAuthor(int postId, int userId) throws SQLException {
        String sql = "SELECT 1 FROM posts WHERE id = ? AND author_id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, postId);
        stmt.setInt(2, userId);
        return stmt.executeQuery().next();
    }
}

