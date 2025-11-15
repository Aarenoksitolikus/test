package ru.itis.oris.test.service;

import ru.itis.oris.test.dao.PostDao;
import ru.itis.oris.test.model.Post;

import java.util.List;
import java.util.Optional;

public class PostService {

    private final PostDao postDao = new PostDao();

    public void save(Post post) {
        postDao.save(post);
    }

    public Optional<Post> findById(int id) {
        return postDao.findById(id);
    }

    public List<Post> findAll() {
        return postDao.findAll();
    }

    public List<Post> findByAuthorId(int authorId) {
        return postDao.findByAuthorId(authorId);
    }

    public void update(Post post) {
        postDao.update(post);
    }

    public void delete(int id) {
        postDao.delete(id);
    }

    public boolean isAuthor(int postId, int userId) {
        return postDao.isAuthor(postId, userId);
    }
}
