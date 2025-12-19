package ru.itis.oris.test.servise;
import ru.itis.oris.test.model.Post;
import ru.itis.oris.test.model.PostDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {

    private final PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postDao.findById(id);
    }

    @Override
    public void createPost(Post post) {
        // выставим дату создания здесь
        post.setCreatedDate(LocalDateTime.now());
        postDao.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postDao.update(post);
    }

    @Override
    public void deletePost(Long id) {
        postDao.delete(id);
    }
}
