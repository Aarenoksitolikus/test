package ru.itis.oris.test.service;

import ru.itis.oris.test.model.User;
import ru.itis.oris.test.model.UserDao;
import ru.itis.oris.test.model.Role;
import ru.itis.oris.test.util.PasswordUtil;
import java.util.Optional;

public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean register(String username, String password, String role) {
        if (userDao.findByUsername(username).isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setHashPassword(PasswordUtil.hashPassword(password));
        user.setRole(Role.valueOf(role.toUpperCase()));

        userDao.save(user);
        return true;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isPresent() && PasswordUtil.checkPassword(password, user.get().getHashPassword())) {
            return user;
        }
        return Optional.empty();
    }
}