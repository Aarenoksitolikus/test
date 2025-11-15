package ru.itis.oris.test.service;

import ru.itis.oris.test.model.PostDao;
import ru.itis.oris.test.model.PostEntity;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostDao postDao = new PostDao();

    public List<PostEntity> getAllPosts() {
        try {
            return postDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public PostEntity getPostById(Long id) {
        try {
            return postDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createPost(String title, String content, Long authorId) {
        try {
            PostEntity post = new PostEntity();
            post.setTitle(title);
            post.setContent(content);
            post.setAuthor_id(authorId.toString());
            post.setCreated_date(LocalDateTime.now().toString());
            postDao.save(post);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePost(Long postId, String title, String content) {
        try {
            PostEntity post = postDao.findById(postId);
            if (post != null) {
                post.setTitle(title);
                post.setContent(content);
                postDao.update(post);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePost(Long postId) {
        try {
            postDao.delete(postId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}