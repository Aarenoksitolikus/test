package ru.itis.oris.test.model.dao;

import ru.itis.oris.test.model.entities.Post;

import java.util.List;

public interface PostDao {
    Post findById(long id);
    List<Post> findAll();
    Post save(Post post);
    void update(Post post);
    void delete(long id);
}
