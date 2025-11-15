package ru.itis.oris.test.model;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(Long id);
}