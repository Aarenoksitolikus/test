package ru.itis.oris.test.service;

import ru.itis.oris.test.model.User;
import ru.itis.oris.test.model.UserDao;
import ru.itis.oris.test.util.PasswordHasher;

import java.util.Optional;

public class AuthService {
    private final UserDao userDao = new UserDao();

    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (PasswordHasher.checkPassword(password, user.getHashPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void register(String username, String password, String role) {
        User user = User.builder()
                .username(username)
                .hashPassword(PasswordHasher.hashPassword(password))
                .role(role)
                .build();
        userDao.save(user);
    }
}