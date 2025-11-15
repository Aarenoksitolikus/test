package ru.itis.oris.test.service;


import ru.itis.oris.test.model.User;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.utils.PasswordHasher;
import java.util.Optional;

public class AuthService {
    private UserDao userDao = new UserDao();

    public boolean register(String username, String password, User.Role role) {
        if (userDao.findByUsername(username).isPresent()) {
            return false;
        }

        String hashedPassword = PasswordHasher.hashPassword(password);
        User user = new User(username, hashedPassword, role);
        userDao.save(user);
        return true;
    }

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

    public boolean validatePassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*\\d.*") &&
                password.matches(".*[a-zA-Z].*");
    }
}