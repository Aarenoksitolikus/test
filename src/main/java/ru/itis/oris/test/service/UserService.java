package ru.itis.oris.test.service;

import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.entities.User;

import java.sql.SQLException;

public class UserService {

    private final UserDao userDao = new UserDao();

    public User getById(int id) {
        try {
            return userDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean createUser(User user) {
        return userDao.save(user);
    }

    public boolean checkPassword(User user, String rawPassword) {
        // здесь должна быть проверка hashPassword
        return PasswordUtil.check(rawPassword, user.getHashPassword());
    }

    public boolean isAdmin(User user) {
        return user.getRole().equals("ADMIN");
    }
}
