package ru.itis.oris.test.servise;
import ru.itis.oris.test.model.Post;

import java.util.List;
import java.util.Optional;

public class PostServise {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    void createPost(Post post);
    void updatePost(Post post);
    void deletePost(Long id);
}

