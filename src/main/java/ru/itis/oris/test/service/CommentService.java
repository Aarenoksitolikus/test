package ru.itis.oris.test.service;

import com.slavikjunior.deorm.orm.EntityManager;
import ru.itis.oris.test.model.Comment;
import ru.itis.oris.test.model.Role;

import java.util.List;
import java.util.Map;

public class CommentService {

    public static List<Comment> getAllComments() {
        return EntityManager.INSTANCE.get(Comment.class);
    }

    public static Comment getCommentById(int id) {
        return EntityManager.INSTANCE.getUnique(Comment.class, Map.of("id", id));
    }

    public static List<Comment> getCommentsByPostId(int postId) {
        return EntityManager.INSTANCE.get(Comment.class, Map.of("post_id", postId));
    }

    public static List<Comment> getCommentsByUserId(int userId) {
        return EntityManager.INSTANCE.get(Comment.class, Map.of("user_id", userId));
    }

    public static Comment createComment(int postId, int userId, String content) {
        Comment comment = new Comment(0, postId, userId, content, new java.sql.Timestamp(System.currentTimeMillis()));
        return EntityManager.INSTANCE.create(comment);
    }

    public static boolean deleteComment(int commentId) {
        try {
            EntityManager.INSTANCE.delete(Comment.class, Map.of("id", commentId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean canDeleteComment(int commentId, int userId, String userRole) {
        Comment comment = getCommentById(commentId);
        if (comment == null) return false;

        if (Role.moderator.name().equals(userRole)) return true;

        return comment.getUserId() == userId;
    }
}