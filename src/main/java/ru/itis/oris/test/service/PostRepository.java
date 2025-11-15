package ru.itis.oris.test.service;

import ru.itis.oris.test.model.entities.Post;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class PostRepository {

    private static final PostRepository INSTANCE = new PostRepository();
    private final Map<Long, Post> storage = new ConcurrentHashMap<>();
    private volatile long sequence = 1;

    private PostRepository() {
        // Добавим тестовые данные
        save(new Post(0, "Первый пост", "Тестовый контент"));
        save(new Post(0, "Второй пост", "Еще один тест"));
    }

    public static PostRepository getInstance() {
        return INSTANCE;
    }

    public synchronized Post save(Post post) {
        long id = sequence++;
        Post newPost = new Post(id, post.getTitle(), post.getContent());
        storage.put(id, newPost);
        return newPost;
    }

    public Post find(long id) {
        return storage.get(id);
    }

    public void update(Post post) {
        storage.put(post.getId(), post);
    }

    public Map<Long, Post> findAll() {
        return storage;
    }
}
