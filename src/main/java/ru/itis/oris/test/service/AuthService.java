package ru.itis.oris.test.service;

import ru.itis.oris.test.model.User;
import ru.itis.oris.test.model.UserDao;
import ru.itis.oris.test.util.PasswordUtil;
import java.util.Optional;

public class AuthService {
    private UserDao userDao = new UserDao();

    public boolean register(String username, String password) {
        if (userDao.findByUsername(username).isPresent()) {
            return false;
        }
        String hash = PasswordUtil.hash(password);
        User user = new User(null, username, hash, "USER");
        userDao.save(user);
        return true;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isPresent() && PasswordUtil.check(password, userOpt.get().getHashPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
}
