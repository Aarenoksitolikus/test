package ru.itis.oris.test.model.dao;

import ru.itis.oris.test.model.entities.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Long save(User user); // returns generated id
}
