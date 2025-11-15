package ru.itis.oris.test.service;

import ru.itis.oris.test.model.dao.UserDao;
import ru.itis.oris.test.model.entity.UserEntity;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public UserEntity authenticateUser(String email, String password) {
        return userDao.get(email, password);
    }
    public void registrateUser(UserEntity user){
        userDao.create(user);
    }
}
