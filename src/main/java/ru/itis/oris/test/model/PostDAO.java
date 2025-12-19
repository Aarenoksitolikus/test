package ru.itis.oris.test.model;
import java.util.List;
import java.util.Optional;
public interface PostDAO {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void save(Post post);
    void update(Post post);
    void delete(Long id);
}
