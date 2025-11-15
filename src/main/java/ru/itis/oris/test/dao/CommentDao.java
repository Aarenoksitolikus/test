package ru.itis.oris.test.dao;

import ru.itis.oris.test.model.Comment;
import ru.itis.oris.test.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    public List<Comment> findByPostId(int postId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.*, u.username FROM comments c JOIN users u ON c.user_id = u.id WHERE c.post_id = ? ORDER BY c.created_date";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setPostId(rs.getInt("post_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setUserName(rs.getString("username"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                comments.add(comment);
            }
        }
        return comments;
    }

    public void save(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (post_id, user_id, content) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comment.getPostId());
            stmt.setInt(2, comment.getUserId());
            stmt.setString(3, comment.getContent());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM comments WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}