package ru.itis.oris.test.model;
import java.util.Optional;
public interface UserDao {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    void save(User user);
}
