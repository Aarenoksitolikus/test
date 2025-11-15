package ru.itis.oris.test.service;

import ru.itis.oris.test.model.dao.UserDao;
import ru.itis.oris.test.model.dao.UserDaoImpl;
import ru.itis.oris.test.model.entities.User;
import ru.itis.oris.test.util.PasswordUtil;

import java.util.Optional;

public class AuthService {
    private final UserDao userDao = new UserDaoImpl();

    public Optional<User> authenticate(String username, String password) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isPresent() && PasswordUtil.verify(password, userOpt.get().getHashPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }

    public Long register(String username, String password) {
        // server-side validation: non-empty, complexity, uniqueness
        if (username == null || username.isBlank() || password == null || password.length() < 8) {
            throw new IllegalArgumentException("Validation failed");
        }
        if (userDao.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        String hash = PasswordUtil.hash(password);
        User user = new User(null, username, hash, "USER");
        return userDao.save(user);
    }
}
